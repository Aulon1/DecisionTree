import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import javax.swing.*;

public class Statistics {

        public static void printStatistics(M5P regressionTree, RegressionTreeBuilder regressionTreeBuilder,
                                           RandomForest randomForest, RandomForestBuilder randomForestBuilder,
                                           Instances[] queries, JPanel[] outputPanels) throws Exception {

            // Panel for the first query: RamStorageCpuEvaluation
            regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY1]);
            randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY1]);
            outputPanels[0].setBorder(BorderFactory.createTitledBorder("RamStorageCpuEvaluation Statistics"));
            TestTrees.testTree(regressionTree, randomForest, queries[Constant.TRAINQUERY1], queries[Constant.TESTQUERY1], outputPanels[0]);

            // Panel for the second query: GpuResolutionScreenSize
            regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY2]);
            randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY2]);
            outputPanels[1].setBorder(BorderFactory.createTitledBorder("GpuResolutionScreenSize Statistics"));
            TestTrees.testTree(regressionTree, randomForest, queries[Constant.TRAINQUERY2], queries[Constant.TESTQUERY2], outputPanels[1]);

            // Panel for the third query: BatteryWeightUserRating
            regressionTree = regressionTreeBuilder.trainRegressionTree(queries[Constant.TRAINQUERY3]);
            randomForest = randomForestBuilder.trainRandomForest(queries[Constant.TRAINQUERY3]);
            outputPanels[2].setBorder(BorderFactory.createTitledBorder("BatteryWeightUserRating Statistics"));
            TestTrees.testTree(regressionTree, randomForest, queries[Constant.TRAINQUERY3], queries[Constant.TESTQUERY3], outputPanels[2]);
        }
    }

