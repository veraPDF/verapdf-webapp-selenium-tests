package com.verapdf.selenium.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.verapdf.selenium.blocks.UploadPage.*;

public class SettingsPage extends BasePage {
    public static final String SELECTOR_LIST_OF_PROFILES = "WCAG 2.1 (All)\n" + "WCAG 2.1 (Extra)\n" + "PDF/UA-1 (Machine)\n" + "PDF/UA-1 (Human)\n" + "Tagged PDF";
    public static final By SELECTOR_UPLOAD_PDF_STEPPER_IS_COMPLETED = By.cssSelector("span > div[class='completed']");
    public static final By SELECTOR_SELECT_SETTINGS_STEPPER_IS_ACTIVE = By.cssSelector("span > div[class='active']");
    public static final By SELECTOR_JOBPROFILE_BUTTON = By.id("jobProfile");
    public static final By SELECTOR_LISTBOX_OF_PROFILES = By.cssSelector("ul[role='listbox']");
    public static final By SELECTOR_WCAG21_EXTRA_PROFILE_IS_SELECTED = By.cssSelector("li[data-value='WCAG_2_1']");
    public static final By SELECTOR_MACHINE_PROFILE_IS_SELECTED = By.cssSelector("li[data-value='PDFUA_1_MACHINE']");
    public static final By SELECTOR_TAGGED_PDF_PROFILE_IS_SELECTED = By.cssSelector("li[data-value='TAGGED_PDF']");
    public static final By SELECTOR_HUMAN_PROFILE_IS_SELECTED = By.cssSelector("li[data-value='PDFUA_1_HUMAN']");
    public static final By SELECTOR_VALIDATE_BUTTON = By.xpath("//button/span[text()='Validate']");


    public SettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void switchToSettingsPage() {
        driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_UPLOAD_PDF_STEPPER);
        verifyElementPresentBySelector(SELECTOR_UPLOAD_PDF_STEPPER_IS_COMPLETED);
        verifyElementPresentBySelector(SELECTOR_SELECT_SETTINGS_STEPPER_IS_ACTIVE);
        verifyElementPresentBySelector(SELECTOR_VALIDATION_STEPPER);
        verifyElementPresentBySelector(SELECTOR_VERIFICATION_RESULT_STEPPER);
    }
}
