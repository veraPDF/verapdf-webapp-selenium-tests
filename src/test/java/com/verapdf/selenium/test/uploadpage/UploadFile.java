package com.verapdf.selenium.test.uploadpage;

import com.verapdf.selenium.pages.BasePageTest;
import com.verapdf.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.verapdf.selenium.blocks.BasePage.SELECTOR_HEADERS_LOGO;
import static com.verapdf.selenium.blocks.UploadPage.SELECTOR_CONFIGURE_JOB_BUTTON;
import static com.verapdf.selenium.blocks.UploadPage.SELECTOR_DROPZONE_TEXT;


public class UploadFile extends BasePageTest {
    private static final String FOLDER = "src/test/resources/com/verapdf/selenium/test/uploadpage/UploadFile/";
    private static final String PATH_FILE = FOLDER + "testFile.pdf";
    private static final By SELECTOR_DROPZONE_FILE_NAME = By.className("dropzone-text");
    private static final By SELECTOR_DROPZONE_TEXT_FILE_SIZE = By.className("dropzone-text__file-size");

    @BeforeMethod
    public void uploadPdf() {
        homePage.switchToUploadFile();
        basePage.dataOnEachPages();
        uploadPage.uploadJob(PATH_FILE);
    }

    @Test
    public void dropZoneIsFilled() {
        waitUntilElementIsPresent(SELECTOR_DROPZONE_TEXT_FILE_SIZE);
        Assert.assertEquals("testFile.pdf - 22.80 KB", driver.findElement(SELECTOR_DROPZONE_FILE_NAME).getText());
        Assert.assertTrue(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).isEnabled());
        Utils.captureScreenshot(driver, "fileInDropzone");
    }

    @Test
    public void returnToStaticUploadPage() {
        waitUntilElementIsPresent(SELECTOR_DROPZONE_TEXT_FILE_SIZE);
        driver.findElement(SELECTOR_HEADERS_LOGO).click();
        verifyElementPresentBySelector(SELECTOR_DROPZONE_TEXT);
        Assert.assertEquals(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).getAttribute("disabled"), "true");
        Utils.captureScreenshot(driver, "dropzoneIsEmpty");
    }
}
