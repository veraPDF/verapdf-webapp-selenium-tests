package com.verapdf.selenium.test.about;

import com.verapdf.selenium.pages.BasePageTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.verapdf.selenium.blocks.BasePage.SELECTOR_ABOUT_PAGE_BUTTON;

public class AboutPage extends BasePageTest {
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
        Assert.assertTrue(driver.findElement(SELECTOR_LINK_VERAPDF_GITHUB).isEnabled());
    }
}
