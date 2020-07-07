package com.verapdf.selenium.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class InspectPage extends BasePage {
    public static final By SELECTOR_BACK_TO_SUMMARY_BUTTON = By.xpath("//button/span[text()='Back to summary']");
    public static final By SELECTOR_SUMMARY_TREE_SUBHEADER = By.xpath("//div[text()='Errors overview']");
    public static final By SELECTOR_INFO_BUTTON = By.cssSelector("button[aria-label='info']");


    public InspectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void toolbarOnInspectPage(String title) {
        dataOnEachPages();
        Assert.assertTrue(driver.findElement(SELECTOR_BACK_TO_SUMMARY_BUTTON).isEnabled());
        driver.findElement(By.xpath("//h1[text()='" + title + "']"));
    }

    public void summaryTree(String content_text,String count_checks) {
        verifyElementPresentBySelector(SELECTOR_SUMMARY_TREE_SUBHEADER);
        driver.findElement(By.xpath("//span[text()='" + content_text + "']"));
        driver.findElement(By.xpath("//span[text()='" + count_checks + "']"));
        Assert.assertTrue(driver.findElement(SELECTOR_INFO_BUTTON).isEnabled());
    }
}
