package com.duallab.verapdf.fw.pageobject;

import com.duallab.verapdf.tools.PropertiesValue;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class MainPage extends BasicPage {

    private static Logger log = Logger.getLogger(MainPage.class.getName());
    private final String demoButton = "//main/div/*[@class='btn']";

    public MainPage(WebDriver driver) throws IOException {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageLoad(driver);

    }

    public MainPage openMainPage() throws IOException {
        log.info("Trying to openMainPage ...");
        return this;
    }

    public WebElement getDemoButton() {
        log.info("Trying to getDemoButton ...");
        WebElement el = waitForAndFindWebElement(By.xpath(demoButton), PropertiesValue.getWaitForDriver());
        return el;
    }

    public AppPage openAppPage() throws IOException {
        log.info("Trying to openAppPage ...");
        waitForAndFindWebElement(By.xpath(demoButton), PropertiesValue.getWaitForDriver()).click();
        waitForAndFindWebElement(By.xpath("//*[@class='dropzone-text']"), PropertiesValue.getWaitForDriver());

        return new AppPage(driver);
    }
}
