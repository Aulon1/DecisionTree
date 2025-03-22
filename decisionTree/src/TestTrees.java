import weka.classifiers.Evaluation;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import javax.swing.*;
import java.awt.*;

public class TestTrees {

    public static void testTree(M5P regressionTree, RandomForest randomForest,
                                Instances trainInstances, Instances testInstances, JPanel outputPanel) throws Exception {

        trainInstances.setClassIndex(trainInstances.numAttributes() - 1);
        testInstances.setClassIndex(testInstances.numAttributes() - 1);

        outputPanel.removeAll();

        outputPanel.setLayout(new GridLayout(1, 1, 30, 30));

        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JTextArea regressionStatsArea = new JTextArea(10, 40);
        regressionStatsArea.setEditable(false);

        JTextArea randomForestStatsArea = new JTextArea(10, 40);
        randomForestStatsArea.setEditable(false);

        Evaluation regressionEval = new Evaluation(trainInstances);
        regressionEval.evaluateModel(regressionTree, testInstances);
        regressionStatsArea.append("------- Regression Tree Evaluation --------\n");
        regressionStatsArea.append(regressionEval.toSummaryString() + "\n");

        Evaluation randomForestEval = new Evaluation(trainInstances);
        randomForestEval.evaluateModel(randomForest, testInstances);
        randomForestStatsArea.append("------- Random Forest Evaluation -------\n");
        randomForestStatsArea.append(randomForestEval.toSummaryString() + "\n");

        statsPanel.add(regressionStatsArea);
        statsPanel.add(randomForestStatsArea);

        outputPanel.add(statsPanel);

        outputPanel.revalidate();
        outputPanel.repaint();
    }




    public static Double[] evaluate(M5P regressionTree, RandomForest randomForest, Instances instances) throws Exception {
        int size = instances.size();
        instances.setClassIndex(instances.numAttributes() - 1);

        Double[] regressionEval = new Double[size];
        Double[] randomForestEval = new Double[size];
        Double[] evaluation = new Double[size];

        for (int i = 0; i < size; i++) {
            regressionEval[i] = regressionTree.classifyInstance(instances.instance(i));
            randomForestEval[i] = randomForest.classifyInstance(instances.instance(i));
            evaluation[i]= (regressionEval[i] + randomForestEval[i]) / 2;
        }

        return evaluation;
    }
}
