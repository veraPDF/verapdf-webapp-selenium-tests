package com.duallab.verapdf.tests.general;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.fw.pageobject.MainPage;
import com.duallab.verapdf.tests.BaseTest;
import com.duallab.verapdf.tools.LogAnalysis;
import com.duallab.verapdf.tools.RetryTest;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.duallab.verapdf.assertion.WebElementAssert.myAssertThat;

public class MainPageGUITest extends BaseTest {
    private static Logger log = Logger.getLogger(MainPageGUITest.class.getName());

    @Test(timeOut = 30000, retryAnalyzer = RetryTest.class)
    public void checkMainPage() throws Exception {
        MainPage mainPage = new MainPage(app.getDriver()).openMainPage();
        log.info("Checking ...");
        
        System.out.println(mainPage.getDemoButton().getAttribute("id"));
        myAssertThat(mainPage.getDemoButton())
                .hasAttributeValue("href", "https://dev.pdf4wcag.duallab.com/validate")
                .hasAttributeValue("class", "btn")
                .hasAttributeValue("id", "try-now-button");
    }

    @BeforeMethod
    public void setup(Method method) {
        log.info("Running Test: " + method.getName());
        app = new ApplicationManager();
        log.info("Done\n\n");
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        LogAnalysis.analyzeLog(app.getDriver(), result.getName());
        app.stop(result);
        log.info("Done\n\n");
    }
}
