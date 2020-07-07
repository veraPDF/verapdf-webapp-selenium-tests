package com.verapdf.selenium.test.settings;

import com.verapdf.selenium.pages.BasePageTest;
import com.verapdf.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.verapdf.selenium.blocks.SettingsPage.*;

public class Profiles extends BasePageTest {
    private static final String FOLDER = "src/test/resources/com/verapdf/selenium/test/settings/Profiles/";
    private static final String PATH_FILE = FOLDER + "testFile.pdf";
    private static final By SELECTOR_UPLOAD_FILES_BUTTON = By.xpath("//button/span[text()='Upload files']");
    private static final By SELECTOR_VALIDATE_BUTTON = By.xpath("//button/span[text()='Validate']");
    private static final By SELECTOR_VALIDATION_PROFILE = By.xpath("//section/form/label[text()='Validation profile']");
    private static final By SELECTOR_DEFAULT_PROFILE_IS_WCAG_ALL = By.xpath("//div[text()='WCAG 2.1 (All)']");

    @Test
    public void allProfilesArePresent() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(PATH_FILE);
        settingsPage.switchToSettingsPage();
        verifyElementPresentBySelector(SELECTOR_VALIDATION_PROFILE);
        Assert.assertTrue(driver.findElement(SELECTOR_DEFAULT_PROFILE_IS_WCAG_ALL).isDisplayed());
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        Utils.captureScreenshot(driver, "ListOfProfiles");
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        Assert.assertTrue(driver.findElement(SELECTOR_UPLOAD_FILES_BUTTON).isEnabled());
        Assert.assertTrue(driver.findElement(SELECTOR_VALIDATE_BUTTON).isEnabled());
    }
}
