package com.verapdf.selenium.blocks;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class StatusPage extends BasePage {
    public static final By SELECTOR_PROGRESS_BAR_IS_PRESENT = By.cssSelector("section.progress");
    public static final By SELECTOR_ERROR_MESSAGE_IS_PRESENT = By.xpath("//h3[text()='Validation process finished with error']");
    public static final By SELECTOR_ERROR_DETAILS_IS_PRESENT = By.xpath("//p[text()=\"Couldn't parse stream\"]");

    public StatusPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void progressPercentageIsPresent() {
        dataOnEachPages();
        statusBar();
        waitUntilElementIsPresent(SELECTOR_PROGRESS_BAR_IS_PRESENT);
    }
}
