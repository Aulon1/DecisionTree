import weka.classifiers.trees.M5P;
import weka.core.Instance;
import weka.core.Instances;

public class RegressionTreeBuilder {

    public M5P trainRegressionTree(Instances data1,boolean statistics) throws Exception {
        try {

            data1.setClassIndex(data1.numAttributes() - 1);

            M5P regressionTree = new M5P();
            regressionTree.setDoNotCheckCapabilities(true);
            regressionTree.setSaveInstances(true);
            regressionTree.setBuildRegressionTree(true);
            //regressionTree.setUseUnsmoothed(true);

            regressionTree.setUnpruned(false);
            //7 if you have a few, but if you have a lot the minimum is 20
            regressionTree.setMinNumInstances(20);
            //alternative for the minimum instances
            //String[] options = weka.core.Utils.splitOptions("-M 4.0");
            //regressionTree.setOptions(options);
            regressionTree.buildClassifier(data1);

            return regressionTree;


        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

}
