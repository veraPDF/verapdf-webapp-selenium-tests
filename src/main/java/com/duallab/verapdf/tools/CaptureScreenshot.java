package com.duallab.verapdf.tools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
    private static String currentDate;
    private static Logger log = Logger.getLogger(CaptureScreenshot.class.getName());

    public static void captureScreenshot(WebDriver driver, String screenshotname, Properties properties) {
        currentDate = new SimpleDateFormat("dd-MM-yy_HH-mm-SS").format(new Date());
        log.info(currentDate);
        String fileName = screenshotname + "_" + currentDate;
        try {
            //driver.manage().window().maximize();
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(properties.getProperty("screenshotLocation") + fileName + ".png"));

            log.info("Screenshot taken in the folder: " + properties.getProperty("screenshotLocation"));
        } catch (Exception excep) {
            log.info("Throwing exception while taking screenshot" + excep.getMessage());
            log.info("Exception e occurred: " + excep.toString() + "\n");
        }
    }
}
