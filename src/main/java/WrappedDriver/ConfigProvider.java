package WrappedDriver;

import java.io.*;
import java.util.Properties;

public class ConfigProvider {

    static File configFile;
    static Properties properties;
    static FileInputStream inputStream;

    private static Properties ConfigurationParameters() throws IOException {
        configFile = new File("TestConfiguration.properties");
        properties = new Properties();
        inputStream = new FileInputStream(configFile);
        properties.load(inputStream);

        return properties;
    }

    public static long getPageLoadTimeout(){
        try{
            long pageLoadTimeout = Long.parseLong(ConfigurationParameters().getProperty("pageLoadTimeout"));
            return pageLoadTimeout;
    }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long getThreadSleepTimeout(){
        try{
            long pageLoadTimeout = Long.parseLong(ConfigurationParameters().getProperty("threadSleepTimeout"));
            return pageLoadTimeout;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected static String getSolutionFolderPath(){
        String folderPath = System.getProperty("user.dir");
        return folderPath;
    }

    public static String getAppsDirectoryPath() {
        String appDir = getSolutionFolderPath() + "//src//apps//";
        return appDir;
    }

    public static String getAndroidApp()  {
        try {
            return ConfigurationParameters().getProperty("androidApp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getHubUrl()  {
        try {
            return ConfigurationParameters().getProperty("hubUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPort()  {
        try {
            return ConfigurationParameters().getProperty("port");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
