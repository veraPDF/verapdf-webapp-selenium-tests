package com.duallab.verapdf.tests.demoTest;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.fw.pageobject.MainPage;
import com.duallab.verapdf.tests.BaseTest;
import com.duallab.verapdf.tools.LogAnalysis;
import com.duallab.verapdf.tools.RetryTest;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class DemoTest extends BaseTest {

    private static Logger log = Logger.getLogger(DemoTest.class.getName());
    MainPage mainPage;

    @Test(retryAnalyzer = RetryTest.class)
    public void demoCheckProfile() throws Exception {
        mainPage = new MainPage(app.getDriver()).openMainPage();
        log.info("Checking ...");
        assertThat(mainPage.getTitle()).isEqualTo("veraPDF viewer");
        then(mainPage.getDemoButton().getText()).isEqualTo("Go to demo");
        then(mainPage.getDemoButton().getAttribute("href")).isEqualTo("https://dev.pdf4wcag.duallab.com/demo");
        then(mainPage.getDemoButton().getCssValue("color")).isEqualTo("rgba(255, 255, 255, 1)");
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
