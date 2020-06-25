package com.verapdf.selenium.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.verapdf.selenium.blocks.UploadPage.*;

public class SettingsPage extends BasePage {
    public static final String SELECTOR_LIST_OF_PROFILES = "WCAG 2.1 (All)\n" + "WCAG 2.1 (Extra)\n" + "PDF/UA-1 (Machine)\n" + "PDF/UA-1 (Human)\n" + "Tagged PDF";
    public static final By SELECTOR_JOBPROFILE_BUTTON = By.cssSelector("#jobProfile");
    public static final By SELECTOR_LISTBOX_OF_PROFILES = By.cssSelector("ul[role='listbox']");
    public static final By SELECTOR_WCAG21_COMPLETE_PROFILE_IS_SELECTED = By.cssSelector("input[value='WCAG_2_1_COMPLETE']");
    public static final By SELECTOR_WCAG21_EXTRA_PROFILE_IS_EXPANDED = By.xpath("//li[text()='WCAG 2.1 (Extra)']");
    public static final By SELECTOR_WCAG21_EXTRA_PROFILE_IS_SELECTED = By.cssSelector("input[value='WCAG_2_1']");
    public static final By SELECTOR_MACHINE_PROFILE_IS_EXPANDED = By.xpath("//li[text()='PDF/UA-1 (Machine)']");
    public static final By SELECTOR_MACHINE_PROFILE_IS_SELECTED = By.cssSelector("input[value='PDFUA_1_MACHINE']");
    public static final By SELECTOR_HUMAN_PROFILE_IS_EXPANDED = By.xpath("//li[text()='PDF/UA-1 (Human)']");
    public static final By SELECTOR_HUMAN_PROFILE_IS_SELECTED = By.cssSelector("input[value='PDFUA_1_HUMAN']");
    public static final By SELECTOR_TAGGED_PDF_PROFILE_IS_EXPANDED = By.xpath("//li[text()='Tagged PDF']");
    public static final By SELECTOR_TAGGED_PDF_PROFILE_IS_SELECTED = By.cssSelector("input[value='TAGGED_PDF']");
    public static final By SELECTOR_VALIDATE_BUTTON = By.xpath("//button/span[text()='Validate']");


    public SettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void switchToSettingsPage() {
        driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).click();
        dataOnEachPages();
        statusBar();
        verifyElementPresentBySelector(SELECTOR_UPLOAD_PDF_STEPPER);
        verifyElementPresentBySelector(SELECTOR_VALIDATION_STEPPER);
        verifyElementPresentBySelector(SELECTOR_VERIFICATION_RESULT_STEPPER);
    }
}
