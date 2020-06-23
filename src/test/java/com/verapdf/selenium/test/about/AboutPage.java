package com.verapdf.selenium.test.about;

import com.verapdf.selenium.pages.BasePageTest;
import com.verapdf.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verapdf.selenium.blocks.BasePage.SELECTOR_ABOUT_PAGE_BUTTON;

public class AboutPage extends BasePageTest {
    private static final By SELECTOR_LINK_WCAG21 = By.linkText("WCAG 2.1");
    private static final By SELECTOR_PDF_UA1 = By.linkText("PDF/UA-1");
    private static final By SELECTOR_MATTERHORN = By.linkText("Matterhorn 1.02");
    private static final By SELECTOR_LINK_VERAPDF_GITHUB = By.linkText("veraPDF GitHub");

    @Test
    public void verifyAboutPage() {
        homePage.switchToUploadFile();
        basePage.dataOnEachPages();
        driver.findElement(SELECTOR_ABOUT_PAGE_BUTTON).click();
        String oldTab = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
        Utils.captureScreenshot(driver, "About page");
        Assert.assertTrue(driver.findElement(SELECTOR_LINK_WCAG21).isEnabled());
        Assert.assertTrue(driver.findElement(SELECTOR_PDF_UA1).isEnabled());
        Assert.assertTrue(driver.findElement(SELECTOR_MATTERHORN).isEnabled());
        Assert.assertTrue(driver.findElement(SELECTOR_LINK_VERAPDF_GITHUB).isEnabled());
    }
}
