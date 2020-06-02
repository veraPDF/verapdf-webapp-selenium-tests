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
    public static final By SELECTOR_DROPZONE_TEXT_FILE_SIZE = By.className("dropzone-text__file-size");

    public UploadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void uploadPdf(String filePath) {
        Assert.assertEquals("veraPDF for WCAG", driver.getTitle());
        verifyElementPresentBySelector(SELECTOR_DROPZONE_TEXT);
        Assert.assertEquals(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).getAttribute("disabled"), "true");
        driver.findElement(SELECTOR_DROPZONE).sendKeys(Utils.absolutePath(filePath));
        driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).click();
    }
}
