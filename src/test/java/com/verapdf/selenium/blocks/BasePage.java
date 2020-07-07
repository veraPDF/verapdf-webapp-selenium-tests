package com.verapdf.selenium.blocks;

import com.verapdf.selenium.pages.BasePageTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class BasePage extends BasePageTest {
    public static final By SELECTOR_TITLE_OF_HTML_PAGE = By.xpath("//title[text()='veraPDF for WCAG']");
    public static final By SELECTOR_HEADERS_LOGO = By.cssSelector("img[src='/demo/static/media/veraPDF-logo-400.8ccff6d5.png']");
    public static final By SELECTOR_ABOUT_PAGE_BUTTON = By.cssSelector("a[href='/demo/about']");
    public static final By SELECTOR_FOOTER = By.className("app-footer");
    public static final By SELECTOR_UPLOAD_PDF_STEPPER = By.xpath("//span[text()='Upload PDF']");
    public static final By SELECTOR_SELECT_SETTINGS_STEPPER = By.xpath("//span[text()='Select settings']");
    public static final By SELECTOR_VALIDATION_STEPPER = By.xpath("//span[text()='Validation']");
    public static final By SELECTOR_VERIFICATION_RESULT_STEPPER = By.xpath("//span[text()='Verification results']");


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dataOnEachPages() {
        verifyElementPresentBySelector(SELECTOR_TITLE_OF_HTML_PAGE);
        verifyElementPresentBySelector(SELECTOR_HEADERS_LOGO);
        verifyElementPresentBySelector(SELECTOR_ABOUT_PAGE_BUTTON);
        Assert.assertEquals("version: 0.1.0", driver.findElement(SELECTOR_FOOTER).getText());
    }

    public void statusBar() {
        verifyElementPresentBySelector(SELECTOR_UPLOAD_PDF_STEPPER);
        verifyElementPresentBySelector(SELECTOR_SELECT_SETTINGS_STEPPER);
        verifyElementPresentBySelector(SELECTOR_VALIDATION_STEPPER);
        verifyElementPresentBySelector(SELECTOR_VERIFICATION_RESULT_STEPPER);
    }
}
