package com.verapdf.selenium.test.inspect;

import com.verapdf.selenium.pages.BasePageTest;
import com.verapdf.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.verapdf.selenium.blocks.InspectPage.SELECTOR_INFO_BUTTON;
import static com.verapdf.selenium.blocks.InspectPage.SELECTOR_SUMMARY_TREE_SUBHEADER;
import static com.verapdf.selenium.blocks.SettingsPage.*;
import static com.verapdf.selenium.blocks.SummaryPage.SELECTOR_INSPECT_ERRORS;

public class InspectPdf extends BasePageTest {
    private static final String FOLDER = "src/test/resources/com/verapdf/selenium/test/inspect/InspectPdf/";
    private static final String SUMMARY_PATH_FILE = FOLDER + "testFile.pdf";

    @Test
    public void inspectDefaultProfile() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        verifyElementPresentBySelector(SELECTOR_WCAG21_COMPLETE_PROFILE_IS_SELECTED);
        Utils.captureScreenshot(driver, "WCAG_ALL_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
        statusPage.progressPercentageIsPresent();
        summaryPage.resultSummaryTitle("testFile.pdf");
        Assert.assertEquals("350 checks passed", driver.findElement(By.cssSelector(".legend-item.legend-item_passed")).getText());
        Assert.assertEquals("4 errors", driver.findElement(By.cssSelector(".legend-item.legend-item_failed")).getText());
        driver.findElement(SELECTOR_INSPECT_ERRORS).click();
        inspectPage.toolbarOnInspectPage("testFile");
        verifyElementPresentBySelector(SELECTOR_SUMMARY_TREE_SUBHEADER);
        Assert.assertTrue(driver.findElement(SELECTOR_INFO_BUTTON).isEnabled());
        Utils.captureScreenshot(driver, "inspectDefaultProfile");
    }

    @Test
    public void inspectOfWcagProfile() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        verifyElementPresentBySelector(SELECTOR_WCAG21_COMPLETE_PROFILE_IS_SELECTED);
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        Utils.captureScreenshot(driver, "Photo");
        driver.findElement(SELECTOR_WCAG21_EXTRA_PROFILE_IS_EXPANDED).click();
        verifyElementPresentBySelector(SELECTOR_WCAG21_EXTRA_PROFILE_IS_SELECTED);
        Utils.captureScreenshot(driver, "WCAG_EXTRA_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
        statusPage.progressPercentageIsPresent();
        summaryPage.resultSummaryTitle("testFile.pdf");
        Assert.assertEquals("1 checks passed", driver.findElement(By.cssSelector(".legend-item.legend-item_passed")).getText());
        Assert.assertEquals("1 errors", driver.findElement(By.cssSelector(".legend-item.legend-item_failed")).getText());
        driver.findElement(SELECTOR_INSPECT_ERRORS).click();
        inspectPage.toolbarOnInspectPage("testFile");
        inspectPage.toolbarOnInspectPage("testFile");
        inspectPage.summaryTree("Pages have different orientation", "1");
        Utils.captureScreenshot(driver, "inspectWcagProfile");
    }

    @Test
    public void inspectOfMachineProfile() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        Utils.captureScreenshot(driver, "Photo");
        driver.findElement(SELECTOR_MACHINE_PROFILE_IS_EXPANDED).click();
        verifyElementPresentBySelector(SELECTOR_MACHINE_PROFILE_IS_SELECTED);
        Utils.captureScreenshot(driver, "MACHINE_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
        statusPage.progressPercentageIsPresent();
        summaryPage.resultSummaryTitle("testFile.pdf");
        driver.findElement(SELECTOR_INSPECT_ERRORS).click();
        inspectPage.toolbarOnInspectPage("testFile");
        inspectPage.summaryTree("Incorrect use of heading levels", "1");
        Utils.captureScreenshot(driver, "inspectMachineProfile");
    }

    @Test
    public void inspectOfHumanProfile() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        Utils.captureScreenshot(driver, "Photo");
        driver.findElement(SELECTOR_HUMAN_PROFILE_IS_EXPANDED).click();
        verifyElementPresentBySelector(SELECTOR_HUMAN_PROFILE_IS_SELECTED);
        Utils.captureScreenshot(driver, "HUMAN_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
        statusPage.progressPercentageIsPresent();
        summaryPage.resultSummaryTitle("testFile.pdf");
        driver.findElement(SELECTOR_INSPECT_ERRORS).click();
        inspectPage.toolbarOnInspectPage("testFile");
        inspectPage.toolbarOnInspectPage("testFile");
        inspectPage.summaryTree("The structure type and attributes of a structure element are not semantically appropriate for the structure element.", "2");
        Utils.captureScreenshot(driver, "inspectHumanProfile");
    }

    @Test
    public void inspectTaggedProfile() {
        homePage.switchToUploadFile();
        uploadPage.uploadJob(SUMMARY_PATH_FILE);
        settingsPage.switchToSettingsPage();
        driver.findElement(SELECTOR_JOBPROFILE_BUTTON).click();
        verifyElementPresentBySelector(SELECTOR_LISTBOX_OF_PROFILES);
        List<WebElement> allOptions = driver.findElements(SELECTOR_LISTBOX_OF_PROFILES);
        Assert.assertEquals(SELECTOR_LIST_OF_PROFILES, allOptions.get(0).getText());
        Utils.captureScreenshot(driver, "TAGGED_LIST_OF_PROFILES");
        driver.findElement(SELECTOR_TAGGED_PDF_PROFILE_IS_EXPANDED).click();
        verifyElementPresentBySelector(SELECTOR_TAGGED_PDF_PROFILE_IS_SELECTED);
        Utils.captureScreenshot(driver, "TAGGED_PROFILE");
        driver.findElement(SELECTOR_VALIDATE_BUTTON).click();
        statusPage.progressPercentageIsPresent();
        summaryPage.resultSummaryTitle("testFile.pdf");
        driver.findElement(SELECTOR_INSPECT_ERRORS).click();
        inspectPage.toolbarOnInspectPage("testFile");
        inspectPage.toolbarOnInspectPage("testFile");
        inspectPage.summaryTree("ISO 19005-2:2011, clause Annex_L, test 6", "1");
        Utils.captureScreenshot(driver, "inspectPageWithTaggedProfile");
    }
}
