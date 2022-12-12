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

    public MainPage(WebDriver driver) throws IOException {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageLoad(driver);
    }

    public MainPage openMainPage() throws IOException {
        log.info("Trying to open mainPage ...");
        MainPage mainPage = new MainPage(driver);
        log.info("Trying to open mainPage ... Done");
        return this;
    }

    public String getTitle() {

        return driver.getTitle();
    }

    public WebElement getDemoButton() {

        WebElement el = waitForAndFindWebElement(By.xpath("//main/div/*[@class='btn']"), PropertiesValue.getWaitForDriver());

        return el;
    }
}
