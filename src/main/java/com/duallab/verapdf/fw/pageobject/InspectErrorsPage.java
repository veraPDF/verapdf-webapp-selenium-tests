package com.duallab.verapdf.fw.pageobject;

import com.duallab.verapdf.tools.PropertiesValue;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class InspectErrorsPage extends BasicPage {

    private static Logger log = Logger.getLogger(InspectErrorsPage.class.getName());

    public InspectErrorsPage(WebDriver driver) throws IOException {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageLoad(driver);
    }

    public String getPDFFileName() {
        log.info("Trying to getPDFFileName ...");
        WebElement el = waitForAndFindWebElement(By.xpath("//*[@class='toolbar']//*[contains(@class, 'toolbar__filename ') and @title]"), PropertiesValue.getWaitForDriver());
        String fileName = el.getAttribute("title");
        return fileName;
    }

    public int getListErrorsSize() {
        log.info("Trying to getListErrorsSize ...");
        int size = waitForAndFindWebElements(By.xpath("//*[@class='summary-tree']//*[@class='MuiListItem-container']"), PropertiesValue.getWaitForDriver()).size();
        return size;
    }
}
