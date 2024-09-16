import weka.classifiers.Evaluation;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import java.util.Random;

public class TestTrees {
    static String boldText = "\033[1m";
    static String reset = "\033[0m";

    public static void testTree(M5P regressionTree, RandomForest randomForest,Instances instances1, Instances instances) throws Exception {

        int size= instances.size();
        instances.setClassIndex(instances.numAttributes()-1);
        instances1.setClassIndex(instances1.numAttributes()-1);
        System.out.println(regressionTree);
        System.out.println(boldText+"\nRegression tree statistics: "+reset);
        Evaluation regressionEvaluations = new Evaluation(instances1);
        regressionEvaluations.evaluateModel(regressionTree, instances);
        System.out.println(regressionEvaluations.toSummaryString());

        System.out.println(boldText+"Random Forest statistics: "+reset);
        Evaluation randomForestEvaluations = new Evaluation(instances1);
        randomForestEvaluations.evaluateModel(randomForest, instances);
        System.out.println(randomForestEvaluations.toSummaryString());

    }
    public static Double[] evaluate(M5P regressionTree, RandomForest randomForest, Instances queries) throws Exception {
        int size= queries.size();
        queries.setClassIndex(queries.numAttributes()-1);
        Double[] regressionEval = new Double[size];
        Double[] randomForestEval = new Double[size];
        Double[] evaluation = new Double[size];
        for (int i = 0; i < size; i++) {
            regressionEval[i] = regressionTree.classifyInstance(queries.instance(i));
            randomForestEval[i]=randomForest.classifyInstance(queries.instance(i));
        }

        for (int i = 0; i < size; i++) {
            double x = (regressionEval[i] + randomForestEval[i]) / 2;
            evaluation[i]= x;
        }
        return evaluation;
//        Evaluation evaluation = new Evaluation(queries);
//        evaluation.evaluateModel(randomForest, queries);
//
//        return "Mean Absolute Error: " + evaluation.meanAbsoluteError()+
//                "\nRoot Mean Squared Error: " + evaluation.rootMeanSquaredError()+
//                "\nCorrelation Coefficient: " + evaluation.correlationCoefficient();

    }
}
