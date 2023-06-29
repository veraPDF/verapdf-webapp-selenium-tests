package com.duallab.verapdf.fw;

import com.duallab.verapdf.fw.pageobject.pdfchecking.PdfboxHelper;
import com.duallab.verapdf.tools.CaptureScreenshot;
import com.duallab.verapdf.tools.LogAnalysis;
import com.duallab.verapdf.utils.PropertiesManager;
import com.duallab.verapdf.tools.PropertiesValue;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

public class ApplicationManager {

    private static final int W = Integer.parseInt(PropertiesValue.getBrowserDimensions()[0]);
    private static final int H = Integer.parseInt(PropertiesValue.getBrowserDimensions()[1]);
    static Logger log = Logger.getLogger(ApplicationManager.class.getName());
    protected String baseUrl, driverStr;
    private Properties properties;
    private WebDriver driver;
    private PdfboxHelper pdfboxHelper;

    public ApplicationManager() {
        this.properties = PropertiesManager.getProperties();
    }

    public WebDriver getDriver() {
        log.info("........................................................");

        if (driver == null) {
            driverStr = properties.getProperty("browser");
            log.info("Selected driver: " + driverStr);

            if (driverStr.equals("firefox")) {
                driver = new FirefoxDriver();
            } else if (driverStr.equals("chrome")) {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
//                options.addArguments("headless");

//              options.addArguments("--incognito");
//              options.addArguments("--no-sandbox");
//              options.addArguments("--disable-gpu");
//              options.addArguments("--disable-dev-shm-usage");
//              options.addArguments("--enable-crash-reporter");
//              options.addArguments("--enable-logging");

//                LoggingPreferences logs = new LoggingPreferences();
//                logs.enable(LogType.BROWSER, Level.ALL);

//                DesiredCapabilities cap = DesiredCapabilities.chrome();
//                cap.setCapability(ChromeOptions.CAPABILITY, options);
//                cap.setCapability(CapabilityType.LOGGING_PREFS, logs);

                driver = new ChromeDriver(options);

            } else {
                throw new Error("Unsupported browser: " + driverStr);
            }

            baseUrl = properties.getProperty("baseUrl");
            log.info("Detected baseUrl: " + baseUrl);

            log.info("Going to start...: " + baseUrl);
            log.info("Using driver...: " + driver.toString());
            driver.get(baseUrl);

            driver.manage().window().setSize(new Dimension(W, H));
            log.info("Using Dimension...: " + driver.manage().window().getSize().toString());
            log.info("........................................................");
        }
        return driver;
    }

    public void stop(ITestResult result) {
        log.info("");
        log.info("Result.getStatus (FAILURE = 2, SKIP = 3, SUCCESS = 1): " + result.getStatus());
        if (ITestResult.FAILURE == result.getStatus() | ITestResult.SKIP == result.getStatus()) {
            log.info("CaptureScreenshot ...");
            CaptureScreenshot.captureScreenshot(getDriver(), result.getName(), properties);
            log.info("CaptureScreenshot: Done\n");
            log.info("Trying LogAnalysis.analyzeLog ...");
            LogAnalysis.analyzeLog(getDriver(), result.getName());
            log.info("LogAnalysis.analyzeLog: Done\n");
            log.info("app.getDriver().quitting: ...");
            getDriver().quit();
            log.info("Quitting: Done\n");
        } else {
            // stop();
            if (driver != null) {
                driver.quit();
            }
            log.info("Done");
        }
    }

    public PdfboxHelper pdfboxHelper() {
        if (pdfboxHelper == null) {
            pdfboxHelper = new PdfboxHelper(this);
        }
        return pdfboxHelper;
    }
}
