package com.duallab.verapdf.fw.pageobject;

import com.duallab.verapdf.tools.PropertiesValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BasicPage {
    private static Logger log = Logger.getLogger(BasicPage.class.getName());
    @FindBy(css = "input[type=file]")
    protected WebElement dropzone;

    protected WebDriver driver;
    List<WebElement> listForValue;
    private ArrayList<String> listOfActualValue;

    public BasicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void waitForPageLoad(WebDriver driver) throws IOException {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                                .equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForHeavyLoadPlaces());
        wait.until(pageLoadCondition);
    }

    public void closeElementByESC() {
        WebElement el_body = waitForAndFindWebElement(By.xpath("//*"), PropertiesValue.getWaitForDriver());
        el_body.sendKeys(Keys.ESCAPE);
    }

    public void waitUntilElementIsVisible(WebElement element) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForDriver());
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (org.openqa.selenium.TimeoutException e) {
            log.info("Exception e during wait.until occurred: " + e.toString() + "\n");
        }
    }

    public void waitInvisibilityOfElementLocated(By locator) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForDriver());
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            log.info("Exception e during wait.until occurred: " + e.toString() + "\n");
        }
    }

    public void waitUntilElementIsVisibleCustomized(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (org.openqa.selenium.TimeoutException e) {
            log.info("Exception e during wait.until occurred: " + e.toString() + "\n");
        }
    }

    public void waitUntilElementIsVisible(By locator) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForNegativeCheck());
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            log.info("Exception e during wait.until occurred: " + e.toString() + "\n");
        }
    }

    public void waitUntilElementIsNotVisible(By locator) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForNegativeCheck());
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            log.info("Exception e during wait.until occurred: " + e.toString() + "\n");
        }
    }

    public void waitUntilElementIsPresent(By locator) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForHeavyLoadPlaces());
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            log.info("Exception e during wait.until occurred: " + e.toString() + "\n");
        }
    }

    public void waitUntilElementIsNotPresent(By locator) throws IOException {
        //WebDriverWait wait = new WebDriverWait(driver, 15);
        int i = 0;
        try {
            while (driver.findElements(locator).size() > 0) {
                log.info("Waiting yet ... ");
                Thread.sleep(100);
                i += 100;
                if (i > PropertiesValue.getWaitForHeavyLoadPlaces()) {
                    break;
                }
            }
            log.info("waitUntilElementIsNotPresent ... finished. Wait time:" + i + "\n");
        } catch (org.openqa.selenium.TimeoutException e) {
            log.info("Exception e during wait.until occurred: " + e.getMessage() + "\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element) throws IOException {
        log.info("waitUntilElementIsClickable starting ..." + element.toString());
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForDriver());
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (org.openqa.selenium.TimeoutException e) {
            log.info("Exception e during waitUntilElementIsClickable occurred: " + e.toString() + "\n");
        }
    }

    public void assertElementSelected(WebElement element) {
        Assert.assertTrue(element.isSelected());
    }

    public void assertElementIsNotSelected(WebElement element) {
        Assert.assertFalse(element.isSelected());
    }

    public void clickItem(WebElement item, String errorMessage) {
        try {
            waitUntilElementIsClickable(item);
            log.info("Going to item.click() ...  ");
            item.click();
            Thread.sleep(500);
            log.info(" item.click() ...  clicked " + "\n");
        } catch (Exception e) {
            log.info("Exception e during clickItem throw: " + e.toString() + "\n");
            throw new Error("Element " + errorMessage + " is not clickable");
        }
    }

    public void scrollToElement(WebElement element) throws Exception { // use the method to focus on element
        log.info("Starting scrollToElement ... ");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(250);
        log.info("scrollToElement ... Done \n");
    }

    public void scrollToParameter(WebElement element) throws Exception { // use the method to focus on element
        log.info("Starting scrollToParameter ... ");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        Thread.sleep(250);
        log.info("scrollToParameter ... Done \n");
    }

    public void scrollToTheBeginning(By locator) throws Exception { // use the method to focus on element
        log.info("Starting scrollToElement ... ");
        WebElement el = waitForAndFindWebElement(locator, PropertiesValue.getWaitForDriver());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop=0;", el);
        Thread.sleep(250);
        log.info("scrollToElement ... Done \n");
    }

    public void checkRightDataParsing(String xpathOfColumn, List<String> listOfExpectedValues) throws Exception {
        waitUntilElementIsPresent(By.xpath(xpathOfColumn));
        listOfActualValue = new ArrayList<>();
        Thread.sleep(500);
        listForValue = driver.findElements(By.xpath(xpathOfColumn));
        for (WebElement element : listForValue) {
            String elementValue = element.getText();
            listOfActualValue.add(elementValue);
        }
        Assert.assertEquals((listOfExpectedValues.size()), (listOfActualValue.size()));
        compareListsValue(listOfExpectedValues, listOfActualValue);
    }

    public void checkRightDataParsingForScreenshot(String xpathOfColumn, List<String> listOfExpectedValues)
            throws Exception {
        waitUntilElementIsPresent(By.xpath(xpathOfColumn));
        listOfActualValue = new ArrayList<>();
        listForValue = driver.findElements(By.xpath(xpathOfColumn));
        for (WebElement element : listForValue) {
            String elementValue = element.getAttribute("src");
            listOfActualValue.add(elementValue);
        }

        assertTrue((listOfExpectedValues.size()) == (listOfActualValue.size()));
        compareListsValue(listOfExpectedValues, listOfActualValue);
    }

    public void compareListsValue(List<String> listExpectedValue, List<String> listActualValue) throws Exception {
        int i = 0;
        for (String textName : listExpectedValue) {
            assertEquals(textName, listActualValue.get(i));
            i++;
        }
    }

    public WebElement getDropzone() throws IOException {
        waitUntilElementIsPresent(By.cssSelector("input[accept='application/pdf']"));
        return dropzone;
    }

    public void enterValueInField(WebElement element, String valueEnter) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();

        element.clear();
        element.sendKeys(valueEnter);
    }

    public void selectCheckbox(WebElement checkboxValue) {
        if (!checkboxValue.isSelected()) {
            Actions actions = new Actions(driver);
            actions.moveToElement(checkboxValue).click().build().perform();
        }
    }

    public void unCheckCheckBox(WebElement checkboxValue) {
        if (checkboxValue.isSelected()) {
            Actions actions = new Actions(driver);
            actions.moveToElement(checkboxValue).click().build().perform();
        }
    }

    public Boolean elementIsPresent(String xPath) {
        Boolean isPresent = driver.findElements(By.xpath(xPath)).size() > 0;
        return isPresent;
    }

    public String getBrowserName() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        return browserName;
    }

    public void assertResultValueWithRegExp(String actualMessage, String expectedMessage) {
        assertTrue(actualMessage.matches(expectedMessage));
    }

    public void assertMessageOfElement(WebElement visibleElement, String message) throws IOException {
        waitUntilElementIsVisible(visibleElement);
        assertEquals(message, visibleElement.getText());
    }

    public void windowHandle(String titlePage, String text) {
        String parentId = driver.getWindowHandle();
        String parentTitle = driver.getTitle();
        try {
            for (String winwowId : driver.getWindowHandles()) {
                String title = driver.switchTo().window(winwowId).getTitle();
                if (title.equals(titlePage)) {
                    //assertEquals("The page does not contain text - " + text, driver.getPageSource().contains(text));
                    Assert.assertTrue(driver.getPageSource().contains(text));
                    driver.close();
                    break;
                }
            }
        } finally {
            driver.switchTo().window(parentId);
        }
        assertEquals(parentTitle, driver.getTitle());
    }

    public void assertScreenshotValue(List<String> columnSelector, String xpath) throws Exception {
        waitUntilElementIsPresent(By.xpath(xpath));
        log.info("Printing By.xpath(xpath)).getAttribute('src' to System.out.println ... ");
        System.out.println(driver.findElement(By.xpath(xpath)).getAttribute("src"));
        checkRightDataParsingForScreenshot(xpath, columnSelector);
    }

    public WebElement waitForAndFindWebElement(By waitFor, int timeOutInSeconds) {

        WebElement el = null;

        try {
            (new WebDriverWait(driver, timeOutInSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(waitFor));
            el = driver.findElement(waitFor);
        } catch (Exception e) {
            log.info("\n\nNothing found ...\n");
            log.info("Exception e occurred: " + e.toString() + "\n");
        }
        return el;
    }

    public List<WebElement> waitForAndFindWebElements(By waitFor, int timeOutInSeconds) {

        List<WebElement> els = null;

        try {
            (new WebDriverWait(driver, timeOutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(waitFor));
            els = driver.findElements(waitFor);
        } catch (Exception e) {
            log.info("\n\nNothing found ...\n");
            log.info("Exception e occurred: " + e.toString() + "\n");
        }
        return els;
    }

    public String findTextToBePresent(By locator, int timeOutInSeconds, String text) {
        String textFromElement = "";
        try {
            (new WebDriverWait(driver, timeOutInSeconds))
                    .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            textFromElement = driver.findElement(locator).getText();
            log.info(text + " found.");
        } catch (Exception e) {
            log.info("\n\nNothing found within " + timeOutInSeconds + " \n");
            log.info("Exception e occurred: " + e + "\n");
        }
        return textFromElement;
    }

    public void prefsButtonClick() throws InterruptedException {
        log.info("Starting 'PrefsButtonClick' ...");
        WebElement el =
                waitForAndFindWebElement(
                        By.xpath("//*[@id='wepreferences']"), PropertiesValue.getWaitForDriver());
        TimeUnit.MILLISECONDS.sleep(1000);
        el.click();
        waitForAndFindWebElement(
                By.xpath("//ion-segment-button//ion-icon[@name='browsers']"),
                PropertiesValue.getWaitForDriver());
        log.info("Done.");
    }
}
