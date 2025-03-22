import weka.classifiers.trees.M5P;
import weka.core.Instances;
public class RegressionTreeBuilder {
    public M5P trainRegressionTree(Instances trainData)
            throws Exception {
        try {
            trainData.setClassIndex(trainData.numAttributes() - 1);
            M5P regressionTree = new M5P();
            regressionTree.setDoNotCheckCapabilities(true);
            regressionTree.setSaveInstances(true);
            regressionTree.setBuildRegressionTree(false);
            regressionTree.setUnpruned(false);
            regressionTree.setDebug(true);
            regressionTree.setMinNumInstances(20);
            regressionTree.buildClassifier(trainData);
            System.out.println(regressionTree);

            return regressionTree;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }
}
