package com.duallab.verapdf.tests.hotTest;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.fw.pageobject.MainPage;
import com.duallab.verapdf.tests.BaseTest;
import com.duallab.verapdf.tools.LogAnalysis;
import com.duallab.verapdf.tools.RetryTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class HotTest extends BaseTest {
    private static Logger log = Logger.getLogger(HotTest.class.getName());
    MainPage mainPage;

    @Test(retryAnalyzer = RetryTest.class)
    public void hotCheckProfile() throws IOException, InterruptedException {
        mainPage = new MainPage(app.getDriver()).openMainPage();
        log.info("Checking ...");
        assertThat(mainPage.getTitle()).isEqualTo("veraPDF viewer");
        then(mainPage.getDemoButton().getText()).isEqualTo("Go to demo");
        then(mainPage.getDemoButton().getAttribute("href")).isEqualTo("https://verapdf.duallab.com/demo");
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
