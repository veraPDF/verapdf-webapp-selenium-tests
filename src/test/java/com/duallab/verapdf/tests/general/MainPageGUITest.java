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

        myAssertThat(mainPage.getDemoButton())
                .hasAttributeValue("href", "https://verapdf.duallab.com/demo")
                .hasAttributeValue("class", "btn")
                .hasCSSValue("color", "rgba(255, 255, 255, 1)")
                .hasCSSValue("background", "rgb(212, 83, 96) none repeat scroll 0% 0% / auto padding-box border-box");
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
