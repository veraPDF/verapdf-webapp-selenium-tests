package com.duallab.verapdf.base;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.TextCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public abstract class AbstractPdf4WcagSelenideConfig {

    private static final Logger LOGGER = LogManager.getLogger(AbstractPdf4WcagSelenideConfig.class);

    private static Properties testProperties;

    private static ChromeOptions chromeOptions;
    private static FirefoxOptions firefoxOptions;
    private static DesiredCapabilities capabilities;

    public static void setupStaticContext(String propertyFile, String baseUrlPropertyName) {
        testProperties = readProperties(propertyFile);
        Configuration.baseUrl = testProperties.getProperty(baseUrlPropertyName);
        Configuration.browser = testProperties.getProperty("browser");
        Configuration.browserSize = testProperties.getProperty("browserSize");
        Configuration.timeout = Long.parseLong(testProperties.getProperty("timeout"));
        Configuration.pollingInterval = Long.parseLong(testProperties.getProperty("pollingInterval"));
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.textCheck = TextCheck.FULL_TEXT;
        capabilities = new DesiredCapabilities();

        applyBrowserDifferences(
                () -> {
                    capabilities.setBrowserName(Browsers.CHROME);
                    chromeOptions = new ChromeOptions()
                            // Enable indication that browser is controlled by automation
                            .addArguments("--enable-automation")
                            // The /dev/shm partition is too small in certain VM environments, causing Chrome to fail
                            // or crash (see http://crbug.com/715363).
                            // Use this flag to work around this issue (a temporary directory will always be used to
                            // create anonymous shared memory files)
                            .addArguments("--disable-dev-shm-usage")
                            // Disables GPU hardware acceleration. If software renderer is not in place, then the GPU
                            // process won't launch
                            .addArguments("--disable-gpu")
                            // Disable extensions
                            .addArguments("--disable-extensions")
                            // Enables web socket connections from the specified origins only. '*' allows any origin.
                            .addArguments("--remote-allow-origins=*");
                    Configuration.browserCapabilities = chromeOptions;
                },
                () -> {
                    capabilities.setBrowserName(Browsers.FIREFOX);
                    firefoxOptions = new FirefoxOptions();
                    Configuration.browserCapabilities = firefoxOptions;
                }
        );
    }

    public static Properties readProperties(String propertiesFile) {
        Properties properties = new Properties();

        readPropertyFile(properties, propertiesFile);

        for (String key : properties.stringPropertyNames()) {
            String property = System.getProperty(key);
            if (property != null) {
                properties.setProperty(key, property);
            }
        }

        return properties;
    }

    public static String getInitializedProperty(String propertyName) {
        if (testProperties == null) {
            throw new IllegalStateException("Call setupStaticContext first");
        }

        return testProperties.getProperty(propertyName);
    }

    public String getProperty(String propertyName) {
        return AbstractPdf4WcagSelenideConfig.getInitializedProperty(propertyName);
    }

    public void retryWithCleanMechanism(Runnable start, Runnable clean, int retryCount, long sleep) {
        int attempt = 1;
        while (true) {
            try {
                start.run();
                break;
            } catch (Exception e) {
                if (attempt++ > retryCount) {
                    throw e;
                } else {
                    clean.run();
                    Selenide.sleep(sleep);
                }
            }
        }
    }

    private static void readPropertyFile(Properties properties, String propertiesFile) {
        File file = new File(propertiesFile);
        try (InputStream is = Files.newInputStream(file.toPath())) {
            properties.load(is);
        } catch (IOException e) {
            LOGGER.warn("I/O exception on editor properties reading", e);
        }
    }

    private static void applyBrowserDifferences(Runnable chrome, Runnable firefox) {
        switch (Configuration.browser) {
            case Browsers.CHROME -> chrome.run();
            case Browsers.FIREFOX -> firefox.run();
            default -> throw new IllegalStateException("Unsupported browser: " + Configuration.browser);
        }
    }
}
