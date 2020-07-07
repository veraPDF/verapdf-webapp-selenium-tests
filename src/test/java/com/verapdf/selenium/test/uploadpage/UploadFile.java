package com.verapdf.selenium.test.uploadpage;

import com.verapdf.selenium.pages.BasePageTest;
import com.verapdf.selenium.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.verapdf.selenium.blocks.BasePage.SELECTOR_HEADERS_LOGO;
import static com.verapdf.selenium.blocks.SettingsPage.SELECTOR_VALIDATE_BUTTON;
import static com.verapdf.selenium.blocks.StatusPage.SELECTOR_ERROR_DETAILS_IS_PRESENT;
import static com.verapdf.selenium.blocks.StatusPage.SELECTOR_ERROR_MESSAGE_IS_PRESENT;
import static com.verapdf.selenium.blocks.UploadPage.*;


public class UploadFile extends BasePageTest {
    private static final String FOLDER = "src/test/resources/com/verapdf/selenium/test/uploadpage/UploadFile/";
    private static final String PATH_FILE = FOLDER + "testFile.pdf";
    private static final String INVALID_PDF = FOLDER + "HeaderIsNotPresent.pdf";


    @Test
    public void dropZoneIsFilled() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(PATH_FILE);
        waitUntilElementIsPresent(SELECTOR_DROPZONE_TEXT_FILE_SIZE);
        Assert.assertEquals("testFile.pdf - 22.81 KB", driver.findElement(SELECTOR_DROPZONE_FILE_NAME).getText());
        Assert.assertTrue(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).isEnabled());
        Utils.captureScreenshot(driver, "dropzoneIsFilled");
    }

    @Test
    public void returnToStaticUploadPage() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(PATH_FILE);
        waitUntilElementIsPresent(SELECTOR_DROPZONE_TEXT_FILE_SIZE);
        driver.findElement(SELECTOR_HEADERS_LOGO).click();
        verifyElementPresentBySelector(SELECTOR_DROPZONE_TEXT);
        Assert.assertEquals(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).getAttribute("disabled"), "true");
        Utils.captureScreenshot(driver, "dropzoneIsEmpty");
    }

    @Test
    public void catchExceptionForInvalidPdf() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(INVALID_PDF);
        waitUntilElementIsPresent(SELECTOR_DROPZONE_TEXT_FILE_SIZE);
        Assert.assertEquals("HeaderIsNotPresent.pdf - 13.25 KB", driver.findElement(SELECTOR_DROPZONE_FILE_NAME).getText());
        driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).click();
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
        statusPage.progressPercentageIsPresent();
        verifyElementPresentBySelector(SELECTOR_ERROR_MESSAGE_IS_PRESENT);
        verifyElementPresentBySelector(SELECTOR_ERROR_DETAILS_IS_PRESENT);
        Utils.captureScreenshot(driver, "descriptionOfError");
    }
}
