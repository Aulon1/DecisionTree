import java.util.Locale;
import java.util.Scanner;

public class Laptops {
    static String boldText = "\033[1m";
    static String reset = "\033[0m";
    private String processorName;
    private String ramType;
    private String gpuName;
    private int id;
    private int price;
    private int ssd;
    private int storage;
    private int ExpandableMemory;
    private int ram;
    private int dedicatedGraphicMemoryCapacity;
    private int touchScreen;
    private int refreshRate;
    private int screenResolution;
    private int Available=1;
    private double baseClockSpeed;
    private double turboClockSpeed;
    private double batteryBackup;
    private double weight;
    private double userRating;
    private double screenSize;
    private double scores;
    private double ramStorageCpuEvaluation;
    private double gpuResolutionScreenSize;
    private double bateryWeightUserRating;


    public Laptops(int id, String processorName, String ramType,
                   String gpuName, int price, int ssd, int storage, int expandableMemory, int ram,
                   int dedicatedGraphicMemoryCapacity, int touchScreen, int refreshRate, int  screenResolution,
                   double turboClockSpeed, double batteryBackup, double weight,double  screenSize,double baseClockSpeed, double userRating) {
        this.processorName = processorName;
        this.ramType = ramType;
        this.gpuName = gpuName;
        this.id = id;
        this.price = price;
        this.ssd = ssd;
        this.storage = storage;
        this.ExpandableMemory = expandableMemory;
        this.ram = ram;
        this.dedicatedGraphicMemoryCapacity = dedicatedGraphicMemoryCapacity;
        this.touchScreen = touchScreen;
        this.refreshRate = refreshRate;
        this.screenResolution =  screenResolution;
        this.baseClockSpeed = baseClockSpeed;
        this.turboClockSpeed = turboClockSpeed;
        this.batteryBackup = batteryBackup;
        this.weight = weight;
        this.userRating = userRating;
        this.screenSize =  screenSize;
    }

    @Override
    public String toString() {
        return "Score="+scores+"ProcessorName='" + processorName + '\'' + ", BaseClockSpeed=" + baseClockSpeed +", TurboClockSpeed=" + turboClockSpeed +
                ",\n RamType='" + ramType + '\'' + ", Ram=" + ram + ", Storage=" + storage + ", Ssd=" + ssd + ", ExpandableMemory=" + ExpandableMemory +
                ",\n GpuName='" + gpuName + '\'' + ", DedicatedGraphicMemoryCapacity=" + dedicatedGraphicMemoryCapacity +
                ",\n ScreenSize=" +  screenSize + ", TouchScreen=" + touchScreen + ", ScreenResolution=" +  screenResolution + ", RefreshRate=" + refreshRate +
                ",\n Weight=" + weight + ", BatteryBackup=" + batteryBackup + ", Price=" + price;
    }
    public double getRamStorageCpuEvaluation() {
        return  ramStorageCpuEvaluation;
    }
    public void setRamStorageCpuEvaluation(double  ramStorageCpuEvaluation) {
        this. ramStorageCpuEvaluation =  ramStorageCpuEvaluation;
    }
    public double getGpuResolutionScreenSize() {
        return gpuResolutionScreenSize;
    }
    public void setGpuResolutionScreenSize(double gpuResolutionScreenSize) {
        this.gpuResolutionScreenSize = gpuResolutionScreenSize;
    }
    public double getBateryWeightUserRating() {
        return bateryWeightUserRating;
    }
    public void setBateryWeightUserRating(double bateryWeightUserRating) {
        this.bateryWeightUserRating = bateryWeightUserRating;
    }
    public String getProcessorName() {
        return processorName;
    }
    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }
    public String getRamType() {
        return this.ramType;
    }
    public void setRamType(String ramType) {
        this.ramType = ramType;
    }
    public String getGpuName() {
        return gpuName;
    }
    public void setGpuName(String gpuName) {
        this.gpuName = gpuName;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getSsd() {
        return ssd;
    }
    public void setSsd(int ssd) {
        this.ssd = ssd;
    }
    public int getStorage() {
        return storage;
    }
    public void setStorage(int storage) {
        this.storage = storage;
    }
    public int getExpandableMemory() {
        return ExpandableMemory;
    }
    public void setExpandableMemory(int expandableMemory) {
        ExpandableMemory = expandableMemory;
    }
    public int getRam() {
        return ram;
    }
    public void setRAM(int ram) {
        this.ram = ram;
    }
    public int getDedicatedGraphicMemoryCapacity() {
        return dedicatedGraphicMemoryCapacity;
    }
    public void setDedicatedGraphicMemoryCapacity(int dedicatedGraphicMemoryCapacity) {
        this.dedicatedGraphicMemoryCapacity = dedicatedGraphicMemoryCapacity;
    }
    public int getTouchScreen() {
        return touchScreen;
    }
    public void setTouchScreen(int touchScreen) {
        this.touchScreen = touchScreen;
    }
    public double getScreenSize() {
        return screenSize;
    }
    public void setScreenSize(double  screenSize) {
         this.screenSize =  screenSize;
    }
    public int getScreenResolution() {
        return screenResolution;
    }
    public double getScores() {
        return this.scores;
    }
    public void setScores(double scores) {
        this.scores = scores;
    }
    public void setScreenResolution(int  screenResolution) {
        this.screenResolution =  screenResolution;
    }


    public int getAvailable() {
        return Available;
    }

    public void setAvailable(int available) {
        Available = 1;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public double getBaseClockSpeed() {
        return baseClockSpeed;
    }

    public void setBaseClockSpeed(double baseClockSpeed) {
        this.baseClockSpeed = baseClockSpeed;
    }

    public double getTurboClockSpeed() {
        return turboClockSpeed;
    }

    public void setTurboClockSpeed(double turboClockSpeed) {
        this.turboClockSpeed = turboClockSpeed;
    }

    public double getBatteryBackup() {
        return batteryBackup;
    }

    public void setBatteryBackup(double batteryBackup) {
        this.batteryBackup = batteryBackup;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserrating(double userRating) {
        this.userRating = userRating;
    }

    public Laptops() {
    }

    public static Laptops addLaptop(){
        Scanner scanner = new Scanner(System.in);
        Laptops laptop= new Laptops();
        System.out.println(boldText+"Price in Euros "+reset);
        laptop.setPrice(scanner.nextInt());
        scanner.nextLine();
        System.out.println(
                boldText+"amd apu dual core a6\n" +
                        "amd apu dual core a9\n" +
                        "amd athlon dual core\n" +
                        "amd dual core\n" +
                        "amd ryzen 3 dual core\n" +
                        "amd ryzen 3 dual core 3rd gen\n" +
                        "amd ryzen 3 hexa core 4th gen\n" +
                        "amd ryzen 3 quad core\n" +
                        "amd ryzen 3 quad core 3rd gen\n" +
                        "amd ryzen 5 dual core\n" +
                        "amd ryzen 5 hexa core\n" +
                        "amd ryzen 5 hexa core 4th gen\n" +
                        "amd ryzen 5 hexa core 5th gen\n" +
                        "amd ryzen 5 octa core\n" +
                        "amd ryzen 5 octa core 11th gen\n" +
                        "amd ryzen 5 quad core\n" +
                        "amd ryzen 5 quad core 3rd gen\n" +
                        "amd ryzen 7 dual core 7th gen\n" +
                        "amd ryzen 7 hexa core 10th gen\n" +
                        "amd ryzen 7 octa core\n" +
                        "amd ryzen 7 octa core 4th gen\n" +
                        "amd ryzen 7 octa core 5th gen\n" +
                        "amd ryzen 7 quad core\n" +
                        "amd ryzen 7 quad core 10th gen\n" +
                        "amd ryzen 7 quad core 3rd gen\n" +
                        "amd ryzen 9 octa core\n" +
                        "amd ryzen 9 octa core 10th gen\n" +
                        "amd ryzen 9 octa core 11th gen\n" +
                        "amd ryzen 9 octa core 5th gen\n" +
                        "amd ryzen 9 octa core 9th gen\n" +
                        "apple m1\n" +
                        "apple m1 pro\n" +
                        "intel celeron dual core\n" +
                        "intel celeron dual core 4th gen\n" +
                        "intel celeron quad core\n" +
                        "intel core i3 10th gen\n" +
                        "intel core i3 11th gen\n" +
                        "intel core i3 7th gen\n" +
                        "intel core i5 10th gen\n" +
                        "intel core i5 11th gen\n" +
                        "intel core i5 12th gen\n" +
                        "intel core i5 7th gen\n" +
                        "intel core i5 8th gen\n" +
                        "intel core i5 9th gen\n" +
                        "intel core i7 10th gen\n" +
                        "intel core i7 11th gen\n" +
                        "intel core i7 12th gen\n" +
                        "intel core i7 8th gen\n" +
                        "intel core i7 9th gen\n" +
                        "intel core i9 10th gen\n" +
                        "intel core i9 11th gen\n" +
                        "intel core i9 12th gen\n" +
                        "intel hexa core i5 10th gen\n" +
                        "intel octa core i7 10th gen\n" +
                        "intel pentium quad core\n" +
                        "intel pentium quad core 10th gen\n" +
                        "intel pentium quad core 11th gen\n" +
                        "intel pentium silver\n" +
                        "intel ryzen 7 hexa core\n" +
                        "mediatek mediatek kompanio 500\n" +
                        "qualcomm snapdragon 7c\n" +
                        "qualcomm snapdragon 7c gen 2\n" +

                        "------------------------------------------------------------------------------------------------\n" +
                        "Pick on of the processor above \n" +
                        "If you don't pick one of the laptops like those above \n" +
                        "the algorithm may not give the accurate result "+reset);
        laptop.setProcessorName(scanner.nextLine().toLowerCase(Locale.ROOT));

        System.out.println(boldText+"Base processor speed in Ghz"+reset);
        laptop.setBaseClockSpeed(scanner.nextDouble());

        System.out.println(boldText+"Turbo processor speed in Ghz"+reset);
        laptop.setTurboClockSpeed(scanner.nextDouble());

        System.out.println(boldText+"1 for SSD 0 for HDD"+reset);
        laptop.setSsd(scanner.nextInt());

        System.out.println(boldText+"Disk space in GB"+reset);
        laptop.setStorage(scanner.nextInt());

        System.out.println(boldText+"1 for Expandable Disk otherwise 0"+reset);
        laptop.setExpandableMemory(scanner.nextInt());

        System.out.println(boldText+"Ram in GB"+reset);
        laptop.setRAM(scanner.nextInt());

        scanner.nextLine();
        System.out.println(boldText+"lpddr5 or unified memory or ddr5 or lpddr4x or lpddr4 or ddr4 or lpddr3 or ddr3 "+reset);
        laptop.setRamType(scanner.nextLine().toLowerCase(Locale.ROOT));

        System.out.println(boldText+"Graphic Card capacity in GB"+reset);
        laptop.setDedicatedGraphicMemoryCapacity(scanner.nextInt());

        scanner.nextLine();
        System.out.println(boldText+"amd radeon\n" +
                "amd radeon 520\n" +
                "amd radeon 5500m\n" +
                "amd radeon 5500u\n" +
                "amd radeon r4\n" +
                "amd radeon r4 (stoney ridge)\n" +
                "amd radeon r5\n" +
                "amd radeon rx 6600m\n" +
                "amd radeon rx 6700m\n" +
                "amd radeon rx 6800m\n" +
                "amd radeon rx vega 10\n" +
                "amd radeon rx6600m\n" +
                "amd radeon vega\n" +
                "amd radeon vega \n" +
                "amd radeon vega 6\n" +
                "amd radeon vega 8\n" +
                "intel hd\n" +
                "intel hd 500\n" +
                "intel hd 520\n" +
                "intel hd 5500\n" +
                "intel hd 620\n" +
                "intel iris\n" +
                "intel iris plus\n" +
                "intel iris xe\n" +
                "intel iris xe max\n" +
                "intel uhd\n" +
                "intel uhd 600\n" +
                "intel uhd 605\n" +
                "intel uhd 620\n" +
                "iris xe\n" +
                "m1\n" +
                "mediatek\n" +
                "nvidia geforce\n" +
                "nvidia geforce gtx\n" +
                "nvidia geforce gtx 1650\n" +
                "nvidia geforce gtx 1650 max q\n" +
                "nvidia geforce gtx 1650 ti\n" +
                "nvidia geforce gtx 1650 ti max-q\n" +
                "nvidia geforce gtx 1660 ti\n" +
                "nvidia geforce gtx mx 330\n" +
                "nvidia geforce mx 110\n" +
                "nvidia geforce mx 130\n" +
                "nvidia geforce mx 230\n" +
                "nvidia geforce mx 250\n" +
                "nvidia geforce mx 330\n" +
                "nvidia geforce mx 350\n" +
                "nvidia geforce mx 450\n" +
                "nvidia geforce rtx\n" +
                "nvidia geforce rtx 2060\n" +
                "nvidia geforce rtx 2070 max-q\n" +
                "nvidia geforce rtx 2080 super max-q\n" +
                "nvidia geforce rtx 3050\n" +
                "nvidia geforce rtx 3050 ti\n" +
                "nvidia geforce rtx 3050ti\n" +
                "nvidia geforce rtx 3060\n" +
                "nvidia geforce rtx 3070\n" +
                "nvidia geforce rtx 3070 ti\n" +
                "nvidia geforce rtx 3080 ti\n" +
                "nvidia quadro p520\n" +
                "nvidia quadro t2000\n" +
                "qualcomm adreno\n" +
                "qualcomm adreno 618 gpugon 7c gen 2\n" +
                "------------------------------------------------------------------------------------------------\n" +
                "Pick on of the processor above \n" +
                "If you don't pick one of the laptops like those above \n" +
                "the algorithm may not give the accurate result"+reset);
        laptop.setGpuName(scanner.nextLine().toLowerCase(Locale.ROOT));

        System.out.println(boldText+"Battery lasting in hours"+reset);
        laptop.setBatteryBackup(scanner.nextDouble());

        System.out.println(boldText+"1 if touchScreen 0 if not"+reset);
        laptop.setTouchScreen(scanner.nextInt());

        System.out.println(boldText+"Screen size in inch (diagonal line)"+reset);
        laptop.setScreenSize(scanner.nextDouble());

        System.out.println(boldText+"Weight in kg"+reset);
        laptop.setWeight(scanner.nextDouble());

        System.out.println(boldText+"Refresh rate in Hz"+reset);
        laptop.setRefreshRate(scanner.nextInt());

        System.out.println(boldText+"Resolution 720 or 1080 or 1440 or 2160 or 2560"+reset);
        laptop.setScreenResolution(scanner.nextInt());
        laptop.setAvailable(1);

        return laptop;

    }

}
