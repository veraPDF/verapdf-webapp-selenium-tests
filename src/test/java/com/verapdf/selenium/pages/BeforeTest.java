package com.verapdf.selenium.pages;

import com.verapdf.selenium.blocks.HomePage;
import com.verapdf.selenium.blocks.UploadPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BeforeTest {
    private static final String baseUrl = "https://verapdf.duallab.com/new/";
    public WebDriver driver;
    protected HomePage homePage;
    protected UploadPage uploadPage;

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
        homePage = new HomePage(driver);
        uploadPage = new UploadPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
