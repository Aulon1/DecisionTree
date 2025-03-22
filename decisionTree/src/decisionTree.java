import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class decisionTree extends JFrame {
    private JTextArea outputAreaMain, outputAreaAdd, outputAreaStatistics, outputAreaFindBest;
    private JButton train1Button, train2Button, train3Button, addLaptopButton, statisticsButton, findBestButton, exitButton, backButtonStatistics, backButtonAdd, backButtonFindBest;
    private JPanel mainPanel, addLaptopPanel, statisticsPanel, findBestPanel;
    private CardLayout cardLayout;

    private RegressionTreeBuilder regressionTreeBuilder;
    private VisualiseTree visualiseTree;
    private DatabaseQueryLoader databaseQueryLoader;
    private Instances[] queries;
    private RandomForestBuilder randomForestBuilder;
    private RandomForest randomForest;
    private M5P regressionTree;
    private Laptops[] bestLaptops;

    public decisionTree() throws Exception {
        setTitle("Laptop Decision Tree");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set up CardLayout for switching panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel.setSize(screenSize);

        regressionTreeBuilder = new RegressionTreeBuilder();
        visualiseTree = new VisualiseTree();
        databaseQueryLoader = new DatabaseQueryLoader();
        queries = databaseQueryLoader.loadQueries();
        randomForestBuilder = new RandomForestBuilder();
        randomForest = new RandomForest();

        setupMainScreen();
        setupAddLaptopScreen();
        setupStatisticsScreen();
        setupFindBestScreen();

        cardLayout.show(mainPanel, "Main Screen");
    }

    private void setupMainScreen() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        train1Button = new JButton("Train 1: RAM/CPU Evaluation");
        train2Button = new JButton("Train 2: GPU/Resolution");
        train3Button = new JButton("Train 3: Battery/Weight/User Rating");
        statisticsButton = new JButton("Statistics");
        addLaptopButton = new JButton("Add New Laptop");
        findBestButton = new JButton("Find Best Laptop");
        exitButton = new JButton("Exit");

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(train1Button, gbc);

        gbc.gridy++;
        buttonPanel.add(train2Button, gbc);

        gbc.gridy++;
        buttonPanel.add(train3Button, gbc);

        gbc.gridy++;
        buttonPanel.add(statisticsButton, gbc);

        gbc.gridy++;
        buttonPanel.add(addLaptopButton, gbc);


        gbc.gridy++;
        buttonPanel.add(findBestButton, gbc);

        gbc.gridy++;
        buttonPanel.add(exitButton, gbc);

        train1Button.addActionListener(e ->this.trainAction(e,queries[Constant.TRAINQUERY1]));
        train2Button.addActionListener(e ->this.trainAction(e,queries[Constant.TRAINQUERY2]));
        train3Button.addActionListener(e ->this.trainAction(e,queries[Constant.TRAINQUERY3]));

        addLaptopButton.addActionListener(e -> cardLayout.show(mainPanel, "Add Laptop"));

        statisticsButton.addActionListener(e -> cardLayout.show(mainPanel, "Statistics"));
        findBestButton.addActionListener(e -> cardLayout.show(mainPanel, "Find Best"));

        exitButton.addActionListener(this::exitAction);

        mainPanel.add(buttonPanel, "Main Screen");
    }


    private void setupAddLaptopScreen() {
        addLaptopPanel = new JPanel(new BorderLayout());

        JLabel addLaptopLabel = new JLabel("Add New Laptop", SwingConstants.CENTER);
        addLaptopPanel.add(addLaptopLabel, BorderLayout.NORTH);

        LaptopInputForm laptopInputForm = new LaptopInputForm(e -> cardLayout.show(mainPanel, "Main Screen"));
        addLaptopPanel.add(laptopInputForm, BorderLayout.CENTER);

        mainPanel.add(addLaptopPanel, "Add Laptop");
    }

    private void setupStatisticsScreen() {
        statisticsPanel = new JPanel(new BorderLayout());

        JPanel statsContentPanel = new JPanel(new GridLayout(3, 1, 10, 10));  // 3 rows for 3 queries

        JPanel[] outputPanels = new JPanel[3];
        JScrollPane[] scrollPanes = new JScrollPane[3];

        for (int i = 0; i < 3; i++) {
            outputPanels[i] = new JPanel(new BorderLayout());
            JTextArea outputArea = new JTextArea();
            outputArea.setEditable(false);
            scrollPanes[i] = new JScrollPane(outputArea);
            outputPanels[i].add(scrollPanes[i], BorderLayout.CENTER);
            statsContentPanel.add(outputPanels[i]);
        }

        statisticsPanel.add(statsContentPanel, BorderLayout.CENTER);

        backButtonStatistics = new JButton("Back");

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.add(backButtonStatistics);

        statisticsPanel.add(backPanel, BorderLayout.SOUTH);

        mainPanel.add(statisticsPanel, "Statistics");

        statisticsButton.addActionListener(e -> {
            try {
                for (JPanel outputPanel : outputPanels) {
                    outputPanel.removeAll();
                }
                Statistics.printStatistics(regressionTree, regressionTreeBuilder, randomForest, randomForestBuilder, queries, outputPanels);

                cardLayout.show(mainPanel, "Statistics");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backButtonStatistics.addActionListener(e -> cardLayout.show(mainPanel, "Main Screen"));
    }



    private JPanel treeVisualizationPanel;

    private void setupTreeVisualizationScreen() {
        treeVisualizationPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Main Screen"));
        treeVisualizationPanel.add(backButton, BorderLayout.SOUTH);

        mainPanel.add(treeVisualizationPanel, "Tree Visualization");
    }

    private void trainAction(ActionEvent e, Instances query) {
        try {
            regressionTree = regressionTreeBuilder.trainRegressionTree(query);

            setupTreeVisualizationScreen();

            visualiseTree.visualiseRegressionTreeInPanel(regressionTree, treeVisualizationPanel);

            cardLayout.show(mainPanel, "Tree Visualization");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setupFindBestScreen() {
        findBestPanel = new JPanel(new BorderLayout());
        findBestPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 3, 3));

        JTextField priceField = new JTextField();
        JComboBox<String> priceOption = new JComboBox<>(new String[]{"Include","Exclude",});
        JComboBox<String> options = new JComboBox<>(new String[]{"All","OnlyRamStorageCpu","GpuResolutionScreenSize","BatteryWeightRating"});
        JComboBox<String> ramTypeField = new JComboBox<>(new String[]{"", "lpddr5", "unified memory", "ddr5", "lpddr4x", "lpddr4", "ddr4", "lpddr3", "ddr3"});
        JTextField ramField = new JTextField();
        JComboBox<String> ssdField = new JComboBox<>(new String[]{"", "1", "0"});
        JTextField storageField = new JTextField();
        JTextField graphicMemoryField = new JTextField();
        JComboBox<String> screenSizeField = new JComboBox<>(new String[]{"", "11.6", "12.4", "13.3", "14", "15.6", "16", "17.3"});

        formPanel.add(new JLabel("Max Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Use Price for Evaluation"));
        formPanel.add(priceOption);
        formPanel.add(new JLabel("Use Evaluation"));
        formPanel.add(options);
        formPanel.add(new JLabel("RAM Type:"));
        formPanel.add(ramTypeField);
        formPanel.add(new JLabel("RAM (GB):"));
        formPanel.add(ramField);
        formPanel.add(new JLabel("SSD (1 for SSD, 0 for HDD):"));
        formPanel.add(ssdField);
        formPanel.add(new JLabel("Min Storage (GB):"));
        formPanel.add(storageField);

        findBestPanel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton findBestButton = new JButton("Find Best Laptops");
        JButton backButton = new JButton("Back");
        findBestButton.setPreferredSize(new Dimension(180, 40));
        backButton.setPreferredSize(new Dimension(180, 40));

        buttonPanel.add(backButton);
        buttonPanel.add(findBestButton);


        findBestPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel outputPanel = new JPanel(new GridLayout(5, 1, 5, 5));

        JTextArea[] outputAreas = new JTextArea[5];
        JButton[] detailButtons = new JButton[5];

        for (int i = 0; i < 5; i++) {
            JPanel laptopPanel = new JPanel(new BorderLayout(5, 5));

            outputAreas[i] = new JTextArea(3, 40);
            outputAreas[i].setEditable(false);
            outputAreas[i].setFont(new Font("Monospaced", Font.PLAIN, 12));
            laptopPanel.add(outputAreas[i], BorderLayout.CENTER);

            detailButtons[i] = new JButton("See Details");
            final int index = i;
            detailButtons[i].addActionListener(e -> {
                if(bestLaptops==null || bestLaptops.length==0){
                    JOptionPane.showMessageDialog(findBestPanel, "No laptops found");

                }else{
                    Minimax.showLaptopDetails(bestLaptops[index]);
                }
            });
            laptopPanel.add(detailButtons[i], BorderLayout.EAST);

            outputPanel.add(laptopPanel);
        }

        findBestPanel.add(outputPanel, BorderLayout.CENTER);

        findBestButton.addActionListener(e -> {
            try {
                for (JTextArea outputArea : outputAreas) {
                    outputArea.setText("");
                }
                bestLaptops = Minimax.findBestLaptops(
                        databaseQueryLoader,
                        regressionTreeBuilder,
                        randomForestBuilder,
                        queries,
                        priceField,
                        ramTypeField,
                        ramField,
                        ssdField,
                        storageField,
                        graphicMemoryField,
                        screenSizeField,
                        options,
                        priceOption
                );

                for (int i = 0; i < Math.min(bestLaptops.length, 5); i++) {
                    Laptops laptop = bestLaptops[i];
                    outputAreas[i].append("Laptop " + (i + 1) + ":\n");
                    outputAreas[i].append("Score: " + laptop.getScores() + "\n");
                    outputAreas[i].append("Price: $" + laptop.getPrice() + "\n");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                outputAreas[0].append("Error: " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Main Screen"));
        mainPanel.add(findBestPanel, "Find Best");
    }

    private void exitAction(ActionEvent e) {
        System.exit(0);
    }
    public static void main(String[] args) throws Exception {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.OFF);


        SwingUtilities.invokeLater(() -> {
            try {
                new decisionTree().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
