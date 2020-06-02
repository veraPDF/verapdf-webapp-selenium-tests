package com.verapdf.selenium.blocks;

import com.verapdf.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UploadPage extends BasePage {
    public static final By SELECTOR_CONFIGURE_JOB_BUTTON = By.cssSelector("button[type='button']");
    public static final By SELECTOR_DROPZONE = By.cssSelector("input[accept='application/pdf']");
    public static final By SELECTOR_DROPZONE_TEXT = By.xpath("//section[text()='Drop some PDF files here, or click to select files']");
    public static final By SELECTOR_UPLOAD_PDF_STEPPER = By.xpath("//span[text()='Upload PDF']");
    public static final By SELECTOR_UPLOAD_PDF_STEPPER_IS_ACTIVE = By.cssSelector("span > div[class='active']");
    public static final By SELECTOR_SELECT_SETTINGS_STEPPER = By.xpath("//span[text()='Select settings']");
    public static final By SELECTOR_VALIDATION_STEPPER = By.xpath("//span[text()='Validation']");
    public static final By SELECTOR_VERIFICATION_RESULT_STEPPER = By.xpath("//span[text()='Verification results']");

    public UploadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void uploadPdf(String filePath) {
        Assert.assertEquals("veraPDF for WCAG", driver.getTitle());
        verifyElementPresentBySelector(SELECTOR_UPLOAD_PDF_STEPPER);
        verifyElementPresentBySelector(SELECTOR_UPLOAD_PDF_STEPPER_IS_ACTIVE);
        verifyElementPresentBySelector(SELECTOR_SELECT_SETTINGS_STEPPER);
        verifyElementPresentBySelector(SELECTOR_VALIDATION_STEPPER);
        verifyElementPresentBySelector(SELECTOR_VERIFICATION_RESULT_STEPPER);
        verifyElementPresentBySelector(SELECTOR_DROPZONE_TEXT);
        Assert.assertEquals(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).getAttribute("disabled"), "true");
        driver.findElement(SELECTOR_DROPZONE).sendKeys(Utils.absolutePath(filePath));
    }
}
