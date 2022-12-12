package com.duallab.verapdf.tests;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.tools.PropertiesValue;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;


public class BaseTest {

    protected static ApplicationManager app;
    private static Logger log = Logger.getLogger(BaseTest.class.getName());

    static {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeSuite
    public void setUpSuite() throws IOException {
        log.info("\n\nStarting suite ... \n");
    }

    public WebDriver getDriver() {
        return app.getDriver();
    }

    public List<WebElement> waitForAndFindWebElements(By waitFor, int timeOutInSeconds) {

        List<WebElement> els = null;

        try {
            (new WebDriverWait(app.getDriver(), timeOutInSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(waitFor));
            els = app.getDriver().findElements(waitFor);
        } catch (Exception e) {
            log.info("\n\nNothing found ...\n");
            log.info("Exception e occurred: " + e.toString() + "\n");
        }
        return els;
    }

    public WebElement waitForAndFindWebElement(By waitFor, int timeOutInSeconds) {
        WebElement el = null;
        try {
            (new WebDriverWait(app.getDriver(), timeOutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(waitFor));
            el = app.getDriver().findElement(waitFor);
        } catch (Exception e) {
            log.info("\n\nNothing found ...\n");
            log.info("Exception e occurred: " + e.toString() + "\n");
        }
        return el;
    }

    public void setBrowserZoomInPercent(String zoomInPercent) {
        log.info("Setting ZoomIn Percent: " + zoomInPercent);
        JavascriptExecutor executor = (JavascriptExecutor) app.getDriver();
        executor.executeScript("document.body.style.zoom = '" + zoomInPercent + "%'");
        log.info("Setting ZoomIn Percent: Done");
    }

    public void closeElementByESC() {
        WebElement el_body = waitForAndFindWebElement(By.xpath("//*"), PropertiesValue.getWaitForDriver());
        el_body.sendKeys(Keys.ESCAPE);
    }

    public Boolean elementIsDisplayed(By waitFor) {
        (new WebDriverWait(app.getDriver(), PropertiesValue.getWaitForDriver()))
                .until(ExpectedConditions.elementToBeClickable(waitFor));
        Boolean isDisplayed = app.getDriver().findElement(waitFor).isDisplayed();
        return isDisplayed;
    }

    public void scrollToTheBeginning(By locator) throws Exception { // use the method to focus on element
        log.info("Starting scrollToElement ... ");
        WebElement el = waitForAndFindWebElement(locator, PropertiesValue.getWaitForDriver());
        ((JavascriptExecutor) app.getDriver()).executeScript("arguments[0].scrollTop=0;", el);
        Thread.sleep(250);
        log.info("scrollToElement ... Done \n");
    }
}
