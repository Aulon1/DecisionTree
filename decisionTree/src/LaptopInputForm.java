import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;

public class LaptopInputForm extends JPanel {
    String[] processors = {
            "amd apu dual core a6", "amd apu dual core a9", "amd athlon dual core",
            "amd dual core", "amd ryzen 3 dual core", "amd ryzen 3 dual core 3rd gen",
            "amd ryzen 3 hexa core 4th gen", "amd ryzen 3 quad core", "amd ryzen 3 quad core 3rd gen",
            "amd ryzen 5 dual core", "amd ryzen 5 hexa core", "amd ryzen 5 hexa core 4th gen",
            "amd ryzen 5 hexa core 5th gen", "amd ryzen 5 octa core", "amd ryzen 5 octa core 11th gen",
            "amd ryzen 5 quad core", "amd ryzen 5 quad core 3rd gen", "amd ryzen 7 dual core 7th gen",
            "amd ryzen 7 hexa core 10th gen", "amd ryzen 7 octa core", "amd ryzen 7 octa core 4th gen",
            "amd ryzen 7 octa core 5th gen", "amd ryzen 7 quad core", "amd ryzen 7 quad core 10th gen",
            "amd ryzen 7 quad core 3rd gen", "amd ryzen 9 octa core", "amd ryzen 9 octa core 10th gen",
            "amd ryzen 9 octa core 11th gen", "amd ryzen 9 octa core 5th gen", "amd ryzen 9 octa core 9th gen",
            "apple m1", "apple m1 pro", "intel celeron dual core", "intel celeron dual core 4th gen",
            "intel celeron quad core", "intel core i3 10th gen", "intel core i3 11th gen",
            "intel core i3 7th gen", "intel core i5 10th gen", "intel core i5 11th gen",
            "intel core i5 12th gen", "intel core i5 7th gen", "intel core i5 8th gen",
            "intel core i5 9th gen", "intel core i7 10th gen", "intel core i7 11th gen",
            "intel core i7 12th gen", "intel core i7 8th gen", "intel core i7 9th gen",
            "intel core i9 10th gen", "intel core i9 11th gen", "intel core i9 12th gen",
            "intel hexa core i5 10th gen", "intel octa core i7 10th gen", "intel pentium quad core",
            "intel pentium quad core 10th gen", "intel pentium quad core 11th gen", "intel pentium silver",
            "intel ryzen 7 hexa core", "mediatek mediatek kompanio 500", "qualcomm snapdragon 7c",
            "qualcomm snapdragon 7c gen 2"
    };

    String[] gpuArray = {
            "amd radeon", "amd radeon 520", "amd radeon 5500m", "amd radeon 5500u",
            "amd radeon r4", "amd radeon r4 (stoney ridge)", "amd radeon r5", "amd radeon rx 6600m",
            "amd radeon rx 6700m", "amd radeon rx 6800m", "amd radeon rx vega 10", "amd radeon rx6600m",
            "amd radeon vega", "amd radeon vega", "amd radeon vega 6", "amd radeon vega 8",
            "intel hd", "intel hd 500", "intel hd 520", "intel hd 5500", "intel hd 620",
            "intel iris", "intel iris plus", "intel iris xe", "intel iris xe max",
            "intel uhd", "intel uhd 600", "intel uhd 605", "intel uhd 620", "iris xe",
            "m1", "mediatek", "nvidia geforce", "nvidia geforce gtx", "nvidia geforce gtx 1650",
            "nvidia geforce gtx 1650 max q", "nvidia geforce gtx 1650 ti", "nvidia geforce gtx 1650 ti max-q",
            "nvidia geforce gtx 1660 ti", "nvidia geforce gtx mx 330", "nvidia geforce mx 110",
            "nvidia geforce mx 130", "nvidia geforce mx 230", "nvidia geforce mx 250",
            "nvidia geforce mx 330", "nvidia geforce mx 350", "nvidia geforce mx 450",
            "nvidia geforce rtx", "nvidia geforce rtx 2060", "nvidia geforce rtx 2070 max-q",
            "nvidia geforce rtx 2080 super max-q", "nvidia geforce rtx 3050", "nvidia geforce rtx 3050 ti",
            "nvidia geforce rtx 3050ti", "nvidia geforce rtx 3060", "nvidia geforce rtx 3070",
            "nvidia geforce rtx 3070 ti", "nvidia geforce rtx 3080 ti", "nvidia quadro p520",
            "nvidia quadro t2000", "qualcomm adreno", "qualcomm adreno 618 gpu", "qualcomm adreno 618 gpugon 7c gen 2"
    };

    private final JTextField priceField;
    private final JTextField baseClockSpeedField;
    private final JTextField turboClockSpeedField;
    private final JTextField storageField;
    private final JTextField ramField;
    private final JTextField graphicMemoryField;
    private final JTextField batteryBackupField;
    private final JTextField screenSizeField;
    private final JTextField weightField;
    private final JTextField refreshRateField;
    private final JTextField resolutionField;
    private final JComboBox<String> processorComboBox;
    private final JComboBox<String> ramTypeComboBox;
    private final JComboBox<String> gpuComboBox;
    private final JComboBox<String> ssdComboBox;
    private final JComboBox<String> expandableMemoryComboBox;
    private final JComboBox<String> touchScreenComboBox;
    private final JButton submitButton;
    private final JButton backButton;
    private Laptops laptop;

    public LaptopInputForm(ActionListener backAction) {
        setLayout(new GridLayout(0, 2, 10, 10));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(new JLabel("Price in Euros:"));
        priceField = new JTextField();
        priceField.setText("500");
        add(priceField);

        add(new JLabel("Processor:"));
        processorComboBox = new JComboBox<>(processors);
        add(processorComboBox);

        add(new JLabel("Base Processor Speed (GHz):"));
        NumberFormat decimalFormat = NumberFormat.getNumberInstance();
        NumberFormatter decimalFormatter = new NumberFormatter(decimalFormat);
        decimalFormatter.setValueClass(Double.class);
        decimalFormatter.setMinimum(0.0);

        baseClockSpeedField = new JFormattedTextField(decimalFormatter);
        baseClockSpeedField.setText("2.1");
        add(baseClockSpeedField);

        add(new JLabel("Turbo Processor Speed (GHz):"));
        turboClockSpeedField = new JFormattedTextField(decimalFormatter);
        turboClockSpeedField.setText("3.2");
        add(turboClockSpeedField);

        add(new JLabel("SSD (1 for SSD, 0 for HDD):"));
        String[] ssdOptions = {"0", "1"};
        ssdComboBox = new JComboBox<>(ssdOptions);
        add(ssdComboBox);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(integerFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0);

        add(new JLabel("Disk Space (GB):"));
        storageField = new JFormattedTextField(numberFormatter);
        storageField.setText("512");
        add(storageField);

        add(new JLabel("Expandable Memory (1 for Yes, 0 for No):"));
        String[] expandableMemoryOptions = {"0", "1"};
        expandableMemoryComboBox = new JComboBox<>(expandableMemoryOptions);
        add(expandableMemoryComboBox);

        add(new JLabel("RAM (GB):"));
        ramField = new JFormattedTextField(numberFormatter);
        ramField.setText("8");
        add(ramField);

        add(new JLabel("RAM Type:"));
        String[] ramTypes = {"lpddr5", "unified memory", "ddr5", "lpddr4x", "lpddr4", "ddr4", "lpddr3", "ddr3"};
        ramTypeComboBox = new JComboBox<>(ramTypes);
        add(ramTypeComboBox);

        add(new JLabel("Graphic Card (GPU):"));
        gpuComboBox = new JComboBox<>(gpuArray);
        add(gpuComboBox);

        add(new JLabel("Graphic Memory Capacity (GB):"));
        graphicMemoryField = new JFormattedTextField(numberFormatter);
        graphicMemoryField.setText("6");
        add(graphicMemoryField);

        add(new JLabel("Battery Backup (hours):"));
        batteryBackupField = new JFormattedTextField(decimalFormat);
        batteryBackupField.setText("4");
        add(batteryBackupField);

        add(new JLabel("Touch Screen (1 for Yes, 0 for No):"));
        String[] touchScreenOptions = {"0", "1"};
        touchScreenComboBox = new JComboBox<>(touchScreenOptions);
        add(touchScreenComboBox);

        add(new JLabel("Screen Size (inches):"));
        screenSizeField =  new JFormattedTextField(decimalFormat);
        screenSizeField.setText("15.6");
        add(screenSizeField);

        add(new JLabel("Weight (kg):"));
        weightField = new JFormattedTextField(decimalFormat);
        weightField.setText("2.4");
        add(weightField);

        add(new JLabel("Refresh Rate (Hz):"));
        refreshRateField = new JFormattedTextField(numberFormatter);
        refreshRateField.setText("60");
        add(refreshRateField);

        add(new JLabel("Resolution (pixels):"));
        resolutionField = new JFormattedTextField(numberFormatter);
        resolutionField.setText("1080");
        add(resolutionField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
                int price = Integer.parseInt(priceField.getText());
                double baseClockSpeed = Double.parseDouble(baseClockSpeedField.getText());
                double turboClockSpeed = Double.parseDouble(turboClockSpeedField.getText());
                int ssd = Integer.parseInt((String) ssdComboBox.getSelectedItem());
                int storage = Integer.parseInt(storageField.getText());
                int expandableMemory = Integer.parseInt((String) expandableMemoryComboBox.getSelectedItem());
                int ram = Integer.parseInt(ramField.getText());
                int graphicMemory = Integer.parseInt(graphicMemoryField.getText());
                double batteryBackup = Double.parseDouble(batteryBackupField.getText());
                int touchScreen = Integer.parseInt((String) touchScreenComboBox.getSelectedItem());
                double screenSize = Double.parseDouble(screenSizeField.getText());
                double weight = Double.parseDouble(weightField.getText());
                int refreshRate = Integer.parseInt(refreshRateField.getText());
                int resolution = Integer.parseInt(resolutionField.getText());

                laptop = new Laptops();
                laptop.setPrice(price);
                laptop.setProcessorName((String) processorComboBox.getSelectedItem());
                laptop.setBaseClockSpeed(baseClockSpeed);
                laptop.setTurboClockSpeed(turboClockSpeed);
                laptop.setSsd(ssd);
                laptop.setStorage(storage);
                laptop.setExpandableMemory(expandableMemory);
                laptop.setRAM(ram);
                laptop.setRamType((String) ramTypeComboBox.getSelectedItem());
                laptop.setDedicatedGraphicMemoryCapacity(graphicMemory);
                laptop.setGpuName((String) gpuComboBox.getSelectedItem());
                laptop.setBatteryBackup(batteryBackup);
                laptop.setTouchScreen(touchScreen);
                laptop.setScreenSize(screenSize);
                laptop.setWeight(weight);
                laptop.setRefreshRate(refreshRate);
                laptop.setScreenResolution(resolution);
                laptop.setAvailable(1);

                DatabaseQueryLoader databaseQueryLoader = new DatabaseQueryLoader();
                try {
                    databaseQueryLoader.insertLaptop(laptop);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(LaptopInputForm.this, "Laptop details added successfully.");

        });
        backButton = new JButton("Back");
        backButton.addActionListener(backAction);
        add(backButton);
        add(submitButton);

    }

    public Laptops getLaptop() {
        return laptop;
    }
}
