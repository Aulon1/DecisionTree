import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Minimax {
    static String boldText = "\033[1m";
    static String reset = "\033[0m";


    public static void assignScores(Laptops[] laptops) {
        // Scoring function: Combine evaluation and inverse of price
        for (Laptops laptop : laptops) {
            laptop.setScores((laptop.getRamStorageCpuEvaluation()+laptop.getGpuResolutionScreenSize()+laptop.getBateryWeightUserRating())/3
                    - (laptop.getPrice()/1000));
        }
        if(laptops.length!=0) {
            double score = laptops[0].getScores();
            for (int i = 1; i < laptops.length; i++) {

                if (laptops[i].getScores() > score) {
                    score = laptops[i].getScores();
                }
            }

        }
        Arrays.sort(laptops, Comparator.comparingDouble(Laptops::getScores).reversed());

    }

    public static void findBestLaptops(DatabaseQueryLoader databaseQueryLoader, M5P regressionTree,
                                       RegressionTreeBuilder regressionTreeBuilder, RandomForest randomForest,
                                       RandomForestBuilder randomForestBuilder,Instances[] queries) throws Exception {

        String whereCondition= getLaptopSpecifics();
        Laptops[] laptops = databaseQueryLoader.getLaptopsFromDatabase(whereCondition);
        laptops = removeNullLaptops(laptops);
        Instances[] filteredInstances= databaseQueryLoader.loadFilteredQuery(whereCondition);
        regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY1],false);
        randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY1],queries[Constant.TESTQUERY1],false);
        Double[] ramStorageCpuEvaluationValues=TestTrees.evaluate(regressionTree,randomForest,filteredInstances[Constant.TESTQUERY1]);
        regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY2],false);
        randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY2],queries[Constant.TESTQUERY2],false);
        Double[] gpuResolutionScreenSizeValues=TestTrees.evaluate(regressionTree,randomForest,filteredInstances[Constant.TESTQUERY2]);
        regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY3],false);
        randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY3],queries[Constant.TESTQUERY3],false);
        Double[] batteryWeightUserRatingValues=TestTrees.evaluate(regressionTree,randomForest,filteredInstances[Constant.TESTQUERY3]);

        for (int i = 0; i < laptops.length; i++) {
            laptops[i].setRamStorageCpuEvaluation(ramStorageCpuEvaluationValues[i]);
            laptops[i].setGpuResolutionScreenSize(gpuResolutionScreenSizeValues[i]);
            laptops[i].setBatteryBackup(batteryWeightUserRatingValues[i]);
        }

        Minimax.assignScores(laptops);
        double score=laptops[0].getScores();
        int x=0;
        for (int i = 0; i < laptops.length; i++) {
            if(laptops[i].getScores()>score-1 && laptops[i].getScores()<score+1)
            {
                if(x<7){
                    x=x+1;
                    System.out.println(boldText+laptops[i]+"\n"+reset);
                }
            }
        }
    }
    private static String getLaptopSpecifics() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(boldText+"Write the maximum price you would pay for a laptop"+reset);
        int inputPrice = scanner.nextInt();
        String where="and price < "+inputPrice;
        StringBuilder stringBuilder= new StringBuilder(where);
        System.out.println(boldText+"Write a minimum value for the next specification or press enter if you don't want to specify\n"+reset);
        Thread.sleep(500);
        System.out.println(boldText+"RamType: lpddr5 or unified memory or ddr5 or lpddr4x or lpddr4 or ddr4 or lpddr3 or ddr3 "+reset);
        scanner.nextLine();
        String ramType = scanner.nextLine();
        if(!ramType.isEmpty()){
            stringBuilder.append(" and ramType =\""+ramType+"\"");
        }
        System.out.println(boldText+"Ram in GB "+reset);
        String ram= scanner.nextLine();
        if(!ram.isEmpty()){
            stringBuilder.append(" and ram>="+ram);
        }
        System.out.println(boldText+"1 for SSD 0 for HDD "+reset);
        String ssd = scanner.nextLine();
        if(!ssd.isEmpty()){
            stringBuilder.append(" and ssd >="+ssd);
        }
        System.out.println(boldText+"Minimum storage in GB "+reset);
        String storage = scanner.nextLine();
        if(!storage.isEmpty()){
            stringBuilder.append(" and storage >="+ssd);
        }
        System.out.println(boldText+"DedicatedGraphicMemoryCapacity in GB "+reset);
        String dedicatedGraphicMemoryCapacity = scanner.nextLine();
        if(!dedicatedGraphicMemoryCapacity.isEmpty()){
            stringBuilder.append(" and dedicatedGraphicMemoryCapacity >="+dedicatedGraphicMemoryCapacity);
        }
        System.out.println(boldText+"Screen size in inch 11.6 || 12.4 || 13.3 || 14 || 15.6 || 16 || 17.3 "+reset);
        String ScreenSize = scanner.nextLine();
        if(!ScreenSize.isEmpty()){
            stringBuilder.append(boldText+" and ScreenSize ="+ScreenSize);
        }
        stringBuilder.append(" order by id");
        where=stringBuilder.toString();
        System.out.println(boldText+"Processing...\n"+reset);
        return where;
    }
    private static Laptops[] removeNullLaptops(Laptops[] laptops) {
        List<Laptops> nonNullLaptops = new ArrayList<>();

        // Copy non-null laptops to the list
        for (Laptops laptop : laptops) {
            if (laptop != null) {
                nonNullLaptops.add(laptop);
            }
        }

        return nonNullLaptops.toArray(new Laptops[0]);
    }
}
