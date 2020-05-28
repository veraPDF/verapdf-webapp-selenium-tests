package com.verapdf.selenium.test.about;

import com.verapdf.selenium.pages.BasePageTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AboutPage extends BasePageTest {
    public static final By SELECTOR_ABOUT_PAGE_BUTTON = By.cssSelector("a[href='/demo/about']");

    @Test
    public void verifyAboutPage() {
        homePage.switchToUploadFile();
        driver.findElement(SELECTOR_ABOUT_PAGE_BUTTON).click();
        Assert.assertEquals("veraPDF for WCAG", driver.getTitle());
    }
}
