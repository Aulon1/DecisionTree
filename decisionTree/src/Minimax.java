import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Minimax {

    public static void assignScores(Laptops[] laptops, String priceOptions, String options,JTextField maxPriceUser) {
        boolean includePrice = "Include".equals(priceOptions);
        System.out.println(priceOptions);
        System.out.println(options);
        for (Laptops laptop : laptops) {
            double baseScore;

            switch (options) {
                case "OnlyRamStorageCpu":
                    //System.out.println("ramonly");
                    baseScore = laptop.getRamStorageCpuEvaluation();
                    break;
                case "GpuResolutionScreenSize":
                   // System.out.println("gpu");
                    baseScore = laptop.getGpuResolutionScreenSize();
                    break;
                case "BatteryWeightRating":
                   // System.out.println("battery");
                    baseScore = laptop.getBateryWeightUserRating();
                    break;
                default:
                    //System.out.println("all");
                    baseScore = (laptop.getRamStorageCpuEvaluation() +
                            laptop.getGpuResolutionScreenSize() +
                            laptop.getBateryWeightUserRating()) / 3;
                    break;
            }

            if (includePrice) {
                double minPrice = 177;
                double maxPrice= Math.min(Double.parseDouble(maxPriceUser.getText()),4444.0);
                double normalizedPrice = (Math.log(laptop.getPrice()) - Math.log(minPrice)) /
                        (Math.log(maxPrice) - Math.log(minPrice));
                baseScore -= normalizedPrice * 2.0;
            }

            laptop.setScores(baseScore);
        }

        Arrays.sort(laptops, Comparator.comparingDouble(Laptops::getScores).reversed());
    }
    public static JFrame showLaptopDetails(Laptops laptop) {
        JFrame detailFrame = new JFrame("Laptop Details");
        detailFrame.setSize(400, 600);
        JTextArea detailArea = new JTextArea();
        detailArea.setEditable(false);
        detailArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        detailArea.append("Score: " + laptop.getScores() + "\n");
        detailArea.append("Processor: " + laptop.getProcessorName() + "\n");
        detailArea.append("Base Clock Speed: " + laptop.getBaseClockSpeed() + " GHz\n");
        detailArea.append("Turbo Clock Speed: " + laptop.getTurboClockSpeed() + " GHz\n");
        detailArea.append("RAM Type: " + laptop.getRamType() + "\n");
        detailArea.append("RAM: " + laptop.getRam() + " GB\n");
        detailArea.append("Storage: " + laptop.getStorage() + " GB\n");
        detailArea.append("SSD: " + (laptop.getSsd() == 1 ? "Yes" : "No") + "\n");
        detailArea.append("GPU: " + laptop.getGpuName() + "\n");
        detailArea.append("Dedicated GPU Memory: " + laptop.getDedicatedGraphicMemoryCapacity() + " GB\n");
        detailArea.append("Screen Size: " + laptop.getScreenSize() + " inches\n");
        detailArea.append("Screen Resolution: " + laptop.getScreenResolution() + "p\n");
        detailArea.append("Refresh Rate: " + laptop.getRefreshRate() + " Hz\n");
        detailArea.append("Weight: " + laptop.getWeight() + " kg\n");
        detailArea.append("Battery Backup: " + laptop.getBatteryBackup() + " hours\n");
        detailArea.append("Price: $" + laptop.getPrice() + "\n\n");

        detailFrame.add(new JScrollPane(detailArea));
        detailFrame.setVisible(true);
        return detailFrame;
    }


    public static Laptops[] findBestLaptops(DatabaseQueryLoader databaseQueryLoader,
                                            RegressionTreeBuilder regressionTreeBuilder,
                                            RandomForestBuilder randomForestBuilder,
                                            Instances[] queries,
                                            JTextField priceField, JComboBox<String> ramTypeField, JTextField ramField, JComboBox<String> ssdField,
                                            JTextField storageField, JTextField graphicMemoryField, JComboBox<String> screenSizeField,
                                            JComboBox<String> options,JComboBox<String> priceOptions ) throws Exception {

        // Step 1: Get where condition based on user input
        String whereCondition = getLaptopSpecifics(priceField, ramTypeField, ramField, ssdField, storageField, graphicMemoryField, screenSizeField);
        // Step 2: Fetch laptops from the database using the specified conditions
        Laptops[] laptops = databaseQueryLoader.getLaptopsFromDatabase(whereCondition);
        laptops = removeNullLaptops(laptops);  // Remove null entries
        // Step 3: Load filtered instances based on the where condition for further evaluation
        Instances[] filteredInstances = databaseQueryLoader.loadFilteredQuery(whereCondition);
        // Step 4: Evaluate laptops using M5P regression tree and RandomForest for each aspect
        // RAM, Storage, CPU Evaluation
        M5P regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY1]);
        RandomForest randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY1]);
        Double[] ramStorageCpuEvaluationValues = TestTrees.evaluate(regressionTree, randomForest, filteredInstances[Constant.TESTQUERY1]);
        // GPU, Resolution, Screen Size Evaluation
        regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY2]);
        randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY2]);
        Double[] gpuResolutionScreenSizeValues = TestTrees.evaluate(regressionTree, randomForest, filteredInstances[Constant.TESTQUERY2]);
        // Battery, Weight Evaluation
        regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY3]);
        randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY3]);
        Double[] batteryWeightUserRatingValues = TestTrees.evaluate(regressionTree, randomForest, filteredInstances[Constant.TESTQUERY3]);
        // Step 5: Assign evaluation values to the corresponding laptops
        for (int i = 0; i < laptops.length; i++) {
            laptops[i].setRamStorageCpuEvaluation(ramStorageCpuEvaluationValues[i]);
            laptops[i].setGpuResolutionScreenSize(gpuResolutionScreenSizeValues[i]);
            laptops[i].setBateryWeightUserRating(batteryWeightUserRatingValues[i]);
        }
        // Step 6: Assign scores and sort laptops
        assignScores(laptops,(String) priceOptions.getSelectedItem(),(String) options.getSelectedItem(),priceField);

        // Step 7: Return the top laptops (sorted by scores)
        Arrays.sort(laptops, Comparator.comparingDouble(Laptops::getScores).reversed());
        return laptops;
    }

    private static String getLaptopSpecifics(JTextField priceField, JComboBox<String> ramTypeField, JTextField ramField, JComboBox<String> ssdField,
                                             JTextField storageField, JTextField graphicMemoryField, JComboBox<String> screenSizeField) {
        StringBuilder whereClause = new StringBuilder("");

        String price = priceField.getText();
        if (!price.isEmpty()) {
            whereClause.append(" AND price < ").append(price);
        }

        String ramType = (String) ramTypeField.getSelectedItem();
        if (ramType != null && !ramType.isEmpty()) {
            whereClause.append(" AND ramType = '").append(ramType).append("'");
        }

        String ram = ramField.getText();
        if (!ram.isEmpty()) {
            whereClause.append(" AND ram >= ").append(ram);
        }

        String ssd = (String) ssdField.getSelectedItem();
        if (ssd != null && !ssd.isEmpty()) {
            whereClause.append(" AND ssd = ").append(ssd);
        }

        String storage = storageField.getText();
        if (!storage.isEmpty()) {
            whereClause.append(" AND storage >= ").append(storage);
        }

        String dedicatedGraphicMemory = graphicMemoryField.getText();
        if (!dedicatedGraphicMemory.isEmpty()) {
            whereClause.append(" AND dedicatedGraphicMemoryCapacity >= ").append(dedicatedGraphicMemory);
        }

        String screenSize = (String) screenSizeField.getSelectedItem();
        if (screenSize != null && !screenSize.isEmpty()) {
            whereClause.append(" AND screenSize = ").append(screenSize);
        }

        whereClause.append(" ORDER BY id");

        return whereClause.toString();
    }

    private static Laptops[] removeNullLaptops(Laptops[] laptops) {
        List<Laptops> nonNullLaptops = new ArrayList<>();
        for (Laptops laptop : laptops) {
            if (laptop != null) {
                nonNullLaptops.add(laptop);
            }
        }
        return nonNullLaptops.toArray(new Laptops[0]);
    }
}


