package com.verapdf.selenium.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BeforeTest {
    public WebDriver driver;
    public static final String baseUrl = "https://verapdf.duallab.com/demo/new-job/files";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeStart() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().setSize(new Dimension(1044, 788));
        driver.manage().timeouts().pageLoadTimeout(60 * 3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
