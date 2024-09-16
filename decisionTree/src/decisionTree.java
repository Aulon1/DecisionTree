import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class decisionTree {

    static String boldText = "\033[1m";
    static String reset = "\033[0m";
    public static void main(String[] args) throws Exception {
        //stop logging
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.OFF);


        boolean end=false;
        RegressionTreeBuilder regressionTreeBuilder = new RegressionTreeBuilder();
        VisualiseTree visualiseTree = new VisualiseTree();
        DatabaseQueryLoader databaseQueryLoader = new DatabaseQueryLoader();
        Instances[] queries = databaseQueryLoader.loadQueries();

        RandomForestBuilder randomForestBuilder = new RandomForestBuilder();
        RandomForest randomForest= new RandomForest();
        M5P regressionTree = new M5P();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if(!end) {
                System.out.print(boldText+"\nEnter a command " +
                        "\nEnter 1 || t1 || q1 || v1 for training ramStorageCpuEvaluation " +
                        "\nEnter 2 || t2 || q2 || v2 for training gpuResolutionScreenSize " +
                        "\nEnter 3 || t3 || q3 || v3 for training batteryWeightUserRating " +
                        "\nEnter s || statistics || analysis for analysis of the regression tree "+
                        "\nEnter a || add || laptop || new to add a new laptop" +
                        "\nEnter b || f || find || best || build for full program " +
                        "\n -----------------------------------------------" +
                        "\n Command -> "+reset);
            }else{
                System.out.print(boldText+"\nEnter another command or press Enter if you want to continue the program. " +
                        "Press X if you want to exit the program\n\n"+reset);
            }
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("x")) {
                System.out.println(boldText+"Exiting the program."+reset);
                break;
            }

            switch (input.toLowerCase()) {

                case "train1", "t1", "query1", "q1", "1" -> {
                    regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY1],true);
                    visualiseTree.visualiseRegressionTree(regressionTree);
                    end=true;

                }
                case "train2", "t2", "query2", "q2", "2" -> {
                    regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY2],true);
                    visualiseTree.visualiseRegressionTree(regressionTree);
                    end=true;

                }
                case "train3", "t3", "query3", "q3", "3" -> {
                    regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY3],true);
                    visualiseTree.visualiseRegressionTree(regressionTree);
                    end=true;

                }
                case "add","a","laptop","new" -> {
                    System.out.println(boldText+"Add new Laptop:"+reset);
                    Laptops laptop=Laptops.addLaptop();
                    System.out.println(laptop);
                    databaseQueryLoader.insertLaptop(laptop);
                    queries = databaseQueryLoader.loadQueries();
                    end=true;

                }
                case "statistics","s" -> {

                    Statistics.printStatistics(regressionTree,regressionTreeBuilder,randomForest,randomForestBuilder,queries);
                    end=true;
                }
                case "best","b","build" -> {
                    Minimax.findBestLaptops(databaseQueryLoader,regressionTree,regressionTreeBuilder,randomForest,randomForestBuilder,queries);
                    end=true;
                }
                default -> end=false;
            }
        }
        scanner.close(); // Close the scanner when done.
        System.exit(0);
    }

}