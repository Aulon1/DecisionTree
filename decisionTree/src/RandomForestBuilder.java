import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.evaluation.output.prediction.PlainText;
import weka.classifiers.meta.CVParameterSelection;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.Utils;

import java.util.Random;

public class RandomForestBuilder {

    public RandomForest trainRandomForest(Instances train,Instances test,boolean statistics) throws Exception {
        try {
            train.setClassIndex(train.numAttributes() - 1);
            test.setClassIndex(test.numAttributes() - 1);

            RandomForest randomForest = new RandomForest();
            M5P regressionTree = new M5P();
            regressionTree.setDoNotCheckCapabilities(true);
            regressionTree.setSaveInstances(true);

            regressionTree.setBuildRegressionTree(true);

            //regressionTree.setUseUnsmoothed(true);
            regressionTree.setUnpruned(false);
            //7 if you have a few, but if you have a lot the minimum is 20
            regressionTree.setMinNumInstances(20);
            regressionTree.buildClassifier(train);

//            randomForest.setDoNotCheckCapabilities(true);
//
//            //Set the maximum depth of the trees (e.g., 10)
            randomForest.setMaxDepth(10);
            randomForest.setNumFeatures(3);
            randomForest.setCalcOutOfBag(true);
            randomForest.setOutputOutOfBagComplexityStatistics(true);
            randomForest.setStoreOutOfBagPredictions(true);
            randomForest.generatePartition(train);
            randomForest.buildClassifier(train);

            return randomForest;

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }


}
