package com.verapdf.selenium.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends BasePage {
    public static final By SELECTOR_LOGO_IS_PRESENT = By.cssSelector("img[src='assets/img/veraPDF-logo-400.png']");
    public static final By SELECTOR_VERAPDF_FOR_WCAG_HEADING = By.xpath("//h2[text()='veraPDF for WCAG']");
    public static final By SELECTOR_VERSION_INFORMATION_IS_PRESENT = By.xpath("//span[text()='Latest version: 0.1.0']");
    public static final By SELECTOR_GO_TO_DEMO_BUTTON = By.cssSelector("a[href='/demo']");

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void switchToUploadFile() {
        Assert.assertEquals("veraPDF viewer", driver.getTitle());
        verifyElementPresentBySelector(SELECTOR_LOGO_IS_PRESENT);
        verifyElementPresentBySelector(SELECTOR_VERAPDF_FOR_WCAG_HEADING);
        verifyElementPresentBySelector(SELECTOR_VERSION_INFORMATION_IS_PRESENT);
        driver.findElement(SELECTOR_GO_TO_DEMO_BUTTON).click();
    }
}
