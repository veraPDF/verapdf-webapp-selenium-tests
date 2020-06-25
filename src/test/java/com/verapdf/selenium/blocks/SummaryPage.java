package com.verapdf.selenium.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SummaryPage extends BasePage {

    public static final By SELECTOR_VALIDATE_ANOTHER_FILE_BUTTON = By.xpath("//button/span[text()='Validate another file']");
    public static final By SELECTOR_INSPECT_ERRORS = By.xpath("//button/span[text()='Inspect errors']");
    public static final By SELECTOR_PASSED_CHECKS = By.cssSelector(".legend-item.legend-item_passed");
    public static final By SELECTOR_FAILED_CHECKS = By.cssSelector(".legend-item.legend-item_failed");

    public SummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void resultSummaryTitle(String title) {
        dataOnEachPages();
        statusBar();
        Assert.assertTrue(driver.findElement(SELECTOR_VALIDATE_ANOTHER_FILE_BUTTON).isEnabled());
        Assert.assertTrue(driver.findElement(SELECTOR_INSPECT_ERRORS).isEnabled());
        driver.findElement(By.xpath("//h2[text()='" + title + "']"));
        verifyElementPresentBySelector(SELECTOR_PASSED_CHECKS);
        verifyElementPresentBySelector(SELECTOR_FAILED_CHECKS);
    }

    public void resultSummaryChecks(String checks_passed, String errors) {
        driver.findElement(By.xpath("//li[text()='" + checks_passed + "']"));
        driver.findElement(By.xpath("//li[text()='" + errors + "']"));
    }
}
