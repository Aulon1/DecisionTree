import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
public class RandomForestBuilder {
    public RandomForest trainRandomForest(Instances trainData)
            throws Exception {
        try {
            trainData.setClassIndex(trainData.numAttributes() - 1);
            RandomForest randomForest = new RandomForest();
            randomForest.setMaxDepth(10);
            randomForest.setNumFeatures(3);
            randomForest.setCalcOutOfBag(true);
            randomForest.setOutputOutOfBagComplexityStatistics(true);
            randomForest.setStoreOutOfBagPredictions(true);
            randomForest.generatePartition(trainData);
            randomForest.buildClassifier(trainData);
            return randomForest;

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
