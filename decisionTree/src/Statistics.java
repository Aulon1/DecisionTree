import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class Statistics {


    static String boldText = "\033[1m";
    static String reset = "\033[0m";

    public static void printStatistics
            (M5P regressionTree, RegressionTreeBuilder regressionTreeBuilder,
             RandomForest randomForest, RandomForestBuilder randomForestBuilder,
             Instances queries[]) throws Exception {

        regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY1],true);
        randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY1],queries[Constant.TESTQUERY1],true);
        System.out.println(boldText+"RamStorageCpuEvaluation statistics\n"+reset);
        TestTrees.testTree(regressionTree,randomForest,queries[Constant.TRAINQUERY1],queries[Constant.TESTQUERY1]);

        System.out.println(boldText+"\n -----------------------------------------------"+reset);
        regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY2],true);
        randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY2],queries[Constant.TESTQUERY2],true);
        System.out.println(boldText+"GpuResolutionScreenSize statistics\n"+reset);
        TestTrees.testTree(regressionTree,randomForest,queries[Constant.TRAINQUERY2],queries[Constant.TESTQUERY2]);

        System.out.println(boldText+"\n -----------------------------------------------"+reset);
        regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY3],true);
        randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY3],queries[Constant.TESTQUERY3],true);
        System.out.println(boldText+"BatteryWeightUserRating statistics\n"+reset);
        TestTrees.testTree(regressionTree,randomForest,queries[Constant.TRAINQUERY3],queries[Constant.TESTQUERY3]);

        System.out.println(boldText+"\n -----------------------------------------------"+reset);

    }
}
