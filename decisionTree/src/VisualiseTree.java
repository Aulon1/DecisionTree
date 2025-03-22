import weka.classifiers.trees.M5P;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import javax.swing.*;
import java.awt.*;

public class VisualiseTree {

    public void visualiseRegressionTreeInPanel(M5P regressionTree, JPanel panel) throws Exception {
        TreeVisualizer tv = new TreeVisualizer(null, regressionTree.graph(), new PlaceNode2());
        panel.add(tv, BorderLayout.CENTER);
        SwingUtilities.invokeLater(() -> {
            panel.revalidate();
            panel.repaint();
            tv.fitToScreen();
        });
    }
}
