package com.verapdf.selenium.test.uploadpage;

import com.verapdf.selenium.pages.BasePageTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.verapdf.selenium.blocks.UploadPage.SELECTOR_CONFIGURE_JOB_BUTTON;
import static com.verapdf.selenium.blocks.UploadPage.SELECTOR_DROPZONE_TEXT;


public class UploadFile extends BasePageTest {
    private static final String FOLDER = "src/test/resources/com/verapdf/selenium/test/uploadpage/UploadFile/";
    private static final String PATH_FILE = FOLDER + "testFile.pdf";
    private static final By SELECTOR_DROPZONE_FILE_NAME = By.className("dropzone-text");
    private static final By SELECTOR_DROPZONE_TEXT_FILE_SIZE = By.className("dropzone-text__file-size");
    private static final By SELECTOR_RETURN_TO_STATIC_UPLOAD_PAGE = By.cssSelector("img[src='/demo/static/media/veraPDF-logo-400.8ccff6d5.png']");


    @Test
    public void uploadPdf() {
        homePage.switchToUploadFile();
        uploadPage.uploadPdf(PATH_FILE);
        waitUntilElementIsPresent(SELECTOR_DROPZONE_TEXT_FILE_SIZE);
        Assert.assertEquals("testFile.pdf - 12.68 KB", driver.findElement(SELECTOR_DROPZONE_FILE_NAME).getText());
        Assert.assertTrue(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).isEnabled());
    }

    @Test
    public void returnToStaticUploadPage() {
        uploadPdf();
        driver.findElement(SELECTOR_RETURN_TO_STATIC_UPLOAD_PAGE).click();
        verifyElementPresentBySelector(SELECTOR_DROPZONE_TEXT);
        Assert.assertEquals(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).getAttribute("disabled"), "true");
    }
}
