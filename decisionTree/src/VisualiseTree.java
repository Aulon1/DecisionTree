import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualiseTree {

    public void visualiseRegressionTree(M5P regressionTree) throws Exception {
        TreeVisualizer tv = new TreeVisualizer(null, regressionTree.graph(), new PlaceNode2());
        JFrame jf = new JFrame("M5P Regression Tree Visualization");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setSize(screenSize);
        jf.add(tv,BorderLayout.CENTER);
        jf.setAutoRequestFocus(true);

//        jf.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                jf.setVisible(false); // Hide the JFrame instead of exiting
//            }
//        });
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        SwingUtilities.invokeLater(() -> {
            jf.setVisible(true);
        });

        jf.requestFocus();

    }
}
