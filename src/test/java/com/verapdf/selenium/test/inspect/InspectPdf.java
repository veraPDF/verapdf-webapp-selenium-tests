package com.verapdf.selenium.test.inspect;

import com.verapdf.selenium.pages.BasePageTest;
import com.verapdf.selenium.utils.Utils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.verapdf.selenium.blocks.SettingsPage.*;

public class InspectPdf extends BasePageTest {
    private static final String FOLDER = "src/test/resources/com/verapdf/selenium/test/inspect/InspectPdf/";
    private static final String SUMMARY_PATH_FILE = FOLDER + "testFile.pdf";

    @Test
    public void inspectDefaultProfile() {
        homePage.switchToUploadFile();
        basePage.dataOnEachPages();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
    }

    @Test
    public void inspectOfWcagProfile() {
        homePage.switchToUploadFile();
        basePage.dataOnEachPages();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        driver.findElement(SELECTOR_WCAG21_EXTRA_PROFILE_IS_SELECTED).click();
        Utils.captureScreenshot(driver, "WCAG_EXTRA_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
    }

    @Test
    public void inspectOfMachineProfile() {
        homePage.switchToUploadFile();
        basePage.dataOnEachPages();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        driver.findElement(SELECTOR_MACHINE_PROFILE_IS_SELECTED).click();
        Utils.captureScreenshot(driver, "MACHINE_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
    }

    @Test
    public void inspectOfHumanProfile() {
        homePage.switchToUploadFile();
        basePage.dataOnEachPages();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        driver.findElement(SELECTOR_HUMAN_PROFILE_IS_SELECTED).click();
        Utils.captureScreenshot(driver, "HUMAN_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
    }

    @Test
    public void inspectTaggedProfile() {
        homePage.switchToUploadFile();
        basePage.dataOnEachPages();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        driver.findElement(SELECTOR_TAGGED_PDF_PROFILE_IS_SELECTED).click();
        Utils.captureScreenshot(driver, "TAGGED_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
    }
}
