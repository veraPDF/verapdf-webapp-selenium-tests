package com.duallab.verapdf.tools;

import com.duallab.verapdf.utils.PropertiesManager;

public class PropertiesValue {

    public static String getPropertiesValue(String propertiesValue) {
        return PropertiesManager.getProperties().getProperty(propertiesValue);
    }

    public static boolean isDemoVersionContext() {
        String demoVersionPropertyValue = PropertiesManager.getProperties().getProperty("demoVersion");
        return demoVersionPropertyValue != null && !demoVersionPropertyValue.isEmpty();
    }

    public static int getWaitForHeavyLoadPlaces() {
        return Integer.parseInt(PropertiesManager.getProperties().getProperty("driverWaitSecondsForHeavyLoadPlaces"));
    }

    public static int getWaitForNegativeCheck() {
        return Integer.parseInt(PropertiesManager.getProperties().getProperty("driverWaitSecondsForNegativeCheck"));
    }

    public static int getWaitForDriver() {
        return Integer.parseInt(PropertiesManager.getProperties().getProperty("driverWaitSeconds"));
    }

    public static int getWaitSecondsForStates() {
        return Integer.parseInt(PropertiesManager.getProperties().getProperty("driverWaitSecondsForStates"));
    }

    public static String getUserName() {
        return PropertiesManager.getProperties().getProperty("userName");
    }

    public static String getPassWord() {
        return PropertiesManager.getProperties().getProperty("passWord");
    }

    public static Double getSimilar() {
        return Double.parseDouble(PropertiesManager.getProperties().getProperty("similar"));
    }
    public static String[] getRegionScreenDimensions() {
        return PropertiesManager.getProperties().getProperty("RegionScreenDimensions").split(",");
    }
    public static String[] getBrowserDimensions() {
        return PropertiesManager.getProperties().getProperty("browserDimension").split(",");
    }
}
