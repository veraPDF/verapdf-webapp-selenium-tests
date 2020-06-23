package com.verapdf.selenium.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Utils {

    private Utils() {

    }

    public static String absolutePath(String path) {
        return new File(path).getAbsolutePath();
    }

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./screenshot/" + screenshotName+ ".png"));
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot");
        }
    }
}
