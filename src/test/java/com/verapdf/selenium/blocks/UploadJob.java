package com.verapdf.selenium.blocks;

import com.verapdf.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UploadJob extends BasePage {
    public static final By SELECTOR_CONFIGURE_JOB_BUTTON = By.cssSelector("button[type='button']");
    public static final By SELECTOR_DROPZONE = By.cssSelector("input[accept='application/pdf']");

    public UploadJob(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void uploadPdf(String filePath) {
        Assert.assertEquals("veraPDF for WCAG", driver.getTitle());
        Assert.assertEquals(driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).getAttribute("disabled"), "true");
        driver.findElement(SELECTOR_DROPZONE).sendKeys(Utils.absolutePath(filePath));
        driver.findElement(SELECTOR_CONFIGURE_JOB_BUTTON).click();
    }
}
