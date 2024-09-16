import weka.core.Instances;
import weka.core.converters.DatabaseLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DatabaseQueryLoader {
    public static final String trainQuery1 ="trainQuery1";
    public static final String trainQuery2 ="trainQuery2";
    public static final String trainQuery3 ="trainQuery3";
    public static final String testQuery1 ="testQuery1";
    public static final String testQuery2 ="testQuery2";
    public static final String testQuery3 ="testQuery3";
    public static final String[] queryFileNames = {
            trainQuery1,
            trainQuery2,
            trainQuery3,
            testQuery1,
            testQuery2,
            testQuery3
    };
    private DatabaseLoader loader;
    private Connection connection;

    public DatabaseQueryLoader() {
        {
            try {
                connection = DriverManager.getConnection(System.getenv("URL"), System.getenv("USERNAME"), System.getenv("PASSWORD"));
                loader = new DatabaseLoader();
                loader.setSource(System.getenv("URL"), System.getenv("USERNAME"), System.getenv("PASSWORD"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Map<String, String> queryFiles(){
        Map<String, String> queryMap = new HashMap<>();
        // Directory of the sql files
        String sqlFilesDirectory = "src/queries";
        // Read each query from its corresponding file
        for (String queryFileName : queryFileNames) {
            String filePath = sqlFilesDirectory + "/" + queryFileName + ".sql";

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                StringBuilder currentQuery = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    currentQuery.append(line).append("\n");
                }

                queryMap.put(queryFileName, currentQuery.toString().trim());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return queryMap;
    }

    public Instances[] loadQueries() throws Exception {
        Map<String, String> queryMap = queryFiles();

        Instances[] dataQueries = new Instances[queryMap.size()];
        for (int i = 0; i < queryMap.size(); i++) {
            loader.setQuery(queryMap.get(queryFileNames[i]));
            dataQueries[i]=loader.getDataSet();
        }
            return dataQueries;
    }
    public Instances[] loadFilteredQuery(String where) throws IOException {
        Map<String, String> queryMap = queryFiles();

        Instances[] dataQueries = new Instances[queryMap.size()];
        for (int i = 0; i < queryMap.size(); i++) {
            String query=queryMap.get(queryFileNames[i]);
            query=query.replace("order by id",where);
            loader.setQuery(query);
            dataQueries[i]=loader.getDataSet();
        }
        return dataQueries;
    }

    public int insertLaptop(Laptops laptop) throws SQLException {
        String insertSQL = "INSERT INTO laptops \n" +
                "(price, processorName, baseClockSpeed, turboClockSpeed, ssd, storage, expandableMemory, ram, ramType, dedicatedGraphicMemoryCapacity," +
                "gpuName,batteryBackup, touchScreen, screenSize, weight, refreshRate, screenResolution, available ) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        preparedStatement.setInt(1, laptop.getPrice());
        preparedStatement.setString(2, laptop.getProcessorName());
        preparedStatement.setDouble(3, laptop.getBaseClockSpeed());
        preparedStatement.setDouble(4, laptop.getTurboClockSpeed());
        preparedStatement.setInt(5, laptop.getSsd());
        preparedStatement.setInt(6, laptop.getStorage());
        preparedStatement.setInt(7, laptop.getExpandableMemory());
        preparedStatement.setInt(8, laptop.getRam());
        preparedStatement.setString(9, laptop.getRamType());
        preparedStatement.setInt(10, laptop.getDedicatedGraphicMemoryCapacity());
        preparedStatement.setString(11, laptop.getGpuName());
        preparedStatement.setDouble(12, laptop.getBatteryBackup());
        preparedStatement.setInt(13, laptop.getTouchScreen());
        preparedStatement.setDouble(14, laptop.getScreenSize());
        preparedStatement.setDouble(15, laptop.getWeight());
        preparedStatement.setInt(16, laptop.getRefreshRate());
        preparedStatement.setInt(17, laptop.getScreenResolution());
        preparedStatement.setInt(18, laptop.getAvailable());
        return preparedStatement.executeUpdate();
    }
    public Laptops[] getLaptopsFromDatabase(String where) throws SQLException {

        String selectLaptops = "SELECT id, price,processorName,baseClockSpeed,turboClockSpeed,ssd,storage," +
                "ExpandableMemory,ram,ramType,dedicatedGraphicMemoryCapacity,gpuName,batteryBackup,touchScreen," +
                "ScreenSize,weight,refreshRate,screenResolution,userRating FROM laptops WHERE available = 1 "+where;

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectLaptops);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int i=0;
            Laptops[] laptops = new Laptops[50];

            // Iterate through the result set and retrieve laptops
            while (resultSet.next()) {
                String processorName= resultSet.getString("processorName");
                String ramType = resultSet.getString("ramType");
                String gpuName = resultSet.getString("gpuName");
                int id=resultSet.getInt("id");
                int price=resultSet.getInt("price");
                int ssd=resultSet.getInt("ssd");
                int storage=resultSet.getInt("storage");
                int ExpandableMemory=resultSet.getInt("ExpandableMemory");
                int ram=resultSet.getInt("ram");
                int dedicatedGraphicMemoryCapacity=resultSet.getInt("dedicatedGraphicMemoryCapacity");
                int touchScreen=resultSet.getInt("touchScreen");
                int refreshRate=resultSet.getInt("refreshRate");
                int screenResolution=resultSet.getInt("screenResolution");
                double ScreenSize=resultSet.getDouble("ScreenSize");
                double turboClockSpeed=resultSet.getDouble("turboClockSpeed");
                double baseClockSpeed=resultSet.getDouble("baseClockSpeed");
                double batteryBackup=resultSet.getDouble("batteryBackup");
                double weight=resultSet.getDouble("weight");
                double userRating=resultSet.getDouble("userRating");

                laptops[i]=new Laptops(id,processorName,ramType,gpuName,price,ssd,storage,ExpandableMemory,ram,
                        dedicatedGraphicMemoryCapacity,touchScreen,refreshRate,screenResolution,turboClockSpeed,batteryBackup,weight,ScreenSize,baseClockSpeed,userRating);
                i++;
            }
            return laptops;
        }
    }

}
