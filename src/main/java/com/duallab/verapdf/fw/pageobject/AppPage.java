package com.duallab.verapdf.fw.pageobject;

import com.duallab.verapdf.tools.PropertiesValue;
import com.duallab.verapdf.utils.UtilClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static com.duallab.verapdf.assertion.WebElementAssert.myAssertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.apache.commons.lang3.StringUtils.chop;

public class AppPage extends BasicPage {

    private static Logger log = Logger.getLogger(AppPage.class.getName());

    public AppPage(WebDriver driver) throws IOException {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageLoad(driver);
    }

    public AppPage dropPDFFile(String fileName) throws IOException {
        log.info("Trying to dropPDFFile ...");
        WebElement el = waitForAndFindWebElement(By.xpath("//*[@class='dropzone__container']"), PropertiesValue.getWaitForDriver());
        WebElement el_input = el.findElement(By.cssSelector("input[type=file]"));
        el_input.sendKeys(UtilClass.canonicalPath(fileName));
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForDriver());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']//*[@class='app-link nav-button nav-button_forward']")));
        return this;
    }

    public AppPage navigateTo_ConfigureJob() {
        log.info("Trying to navigateToConfigureJob ...");
        WebElement el = waitForAndFindWebElement(By.xpath("//*[@class='MuiButton-label']"), PropertiesValue.getWaitForDriver());
        el.click();
        return this;
    }

    public AppPage selectProfile(String dataValue) {
        log.info("Trying to selectProfile ...");
        log.info("Trying to get the dataValues list  ...");
        WebElement wb_el = waitForAndFindWebElement(By.xpath("//*[@class='MuiFormControl-root select-form-controller']"), PropertiesValue.getWaitForDriver());
        wb_el.click();
        waitForAndFindWebElement(By.xpath("//*[@class='MuiPaper-root MuiMenu-paper MuiPopover-paper MuiPaper-elevation8 MuiPaper-rounded']"), PropertiesValue.getWaitForDriver());

        List<WebElement> els = waitForAndFindWebElements(By.xpath("//*[contains(@class, 'MuiListItem-button') and @tabindex and @role='option']"), PropertiesValue.getWaitForDriver());

        log.info("Checking the number of the Profiles... : " + els.size()) ;
        assertThat(els.size()).isEqualTo(Integer.parseInt(PropertiesValue.getPropertiesValue("profilesNumber")));
        els.forEach(webEl -> myAssertThat(webEl).hasAttributeValue("role", "option"));

        log.info("Trying to select by dataValue ...");
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForDriver());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='menu-']//*[@data-value='" + dataValue + "']")));

        WebElement wb_el_profileSelection = waitForAndFindWebElement(By.xpath("//*[@id='menu-']//*[@data-value='" + dataValue + "']"), PropertiesValue.getWaitForDriver());
        wb_el_profileSelection.click();
        return this;
    }

    public int validate() {
        log.info("Trying to validate  ... ");
        WebDriverWait wait = new WebDriverWait(driver, PropertiesValue.getWaitForDriver());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='page-navigation-text__end']//*[@class='MuiButton-label']")));

        log.info("Trying to click on Validate button  ... ");
        WebElement el_button = waitForAndFindWebElement(By.xpath("//*[@class='page-navigation-text__end']//*[@class='MuiButton-label']"), PropertiesValue.getWaitForDriver());
        el_button.click();

        log.info("Waiting in queue  ... ");
        WebDriverWait wait_progress__percentage = new WebDriverWait(driver, PropertiesValue.getWaitForHeavyLoadPlaces());
        wait_progress__percentage.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='progress__percentage']")));
        log.info("Done.");

        log.info("Waiting legend  ... ");
        waitForAndFindWebElement(By.xpath("//*[@class='legend']"), PropertiesValue.getWaitForDriver());
        //waitForAndFindWebElement(By.xpath("//*[contains(@href, 'result-details')]"), PropertiesValue.getWaitForDriver());
        String text = chop(waitForAndFindWebElement(By.xpath("//*[@id='app']//*[@class='legend']//*"), PropertiesValue.getWaitForDriver()).getText());
        int compliant = Integer.parseInt(text.split(" ")[0]);
        log.info("Done.");
        return compliant;
    }

    public InspectErrorsPage navigateTo_InspectErrorsPage() throws IOException {
        log.info("Trying to navigateToInspectErrorsPage  ... ");
        waitForAndFindWebElement(By.xpath("//*[contains(@href, 'result-details')]"), PropertiesValue.getWaitForDriver()).click();
        waitForAndFindWebElement(By.xpath("//*[@class='inspect']//*[@class='toolbar']"), PropertiesValue.getWaitForDriver());
        return new InspectErrorsPage(driver);
    }
}
