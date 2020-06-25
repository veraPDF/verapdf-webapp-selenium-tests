package com.verapdf.selenium.blocks;

import com.verapdf.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UploadPage extends BasePage {
    public static final By SELECTOR_DROPZONE_FILE_NAME = By.className("dropzone-text");
    public static final By SELECTOR_DROPZONE_TEXT_FILE_SIZE = By.className("dropzone-text__file-size");
    public static final By SELECTOR_DROPZONE_CONTAINER = By.cssSelector("input[accept='application/pdf']");
    public static final By SELECTOR_DROPZONE_TEXT = By.xpath("//section[text()='Drop a PDF file, or click to select a file\']");
    //public static final By SELECTOR_USE_CUSTOM_SETTINGS_CHECKBOX = ;
    public static final By SELECTOR_CONFIGURE_JOB_BUTTON = By.cssSelector("button[type='button']");


    public UploadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void uploadJob(String filePath) {
        dataOnEachPages();
        statusBar();
        verifyElementPresentBySelector(SELECTOR_DROPZONE_TEXT);
        Assert.assertEquals(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).getAttribute("disabled"), "true");
        driver.findElement(SELECTOR_DROPZONE_CONTAINER).sendKeys(Utils.absolutePath(filePath));
    }
}
