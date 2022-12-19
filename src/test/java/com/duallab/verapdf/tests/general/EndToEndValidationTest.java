package com.duallab.verapdf.tests.general;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.fw.pageobject.AppPage;
import com.duallab.verapdf.fw.pageobject.InspectErrorsPage;
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


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class EndToEndValidationTest extends BaseTest {
    private static Logger log = Logger.getLogger(EndToEndValidationTest.class.getName());
    MainPage mainPage;
    private final String FILE_NAME = "test_6-3-8-t01-fail-a.pdf";
    private final String PROFILE_NAME = "PDFUA_1_MACHINE";
    private final String PROFILE_NAME2 = "WCAG_2_1_DEV"; // 100 compliant


    @Test(timeOut = 30000, retryAnalyzer = RetryTest.class)
    public void checkEndToEndValidation() throws Exception {
        mainPage = new MainPage(app.getDriver()).openMainPage();
        log.info("Checking ...");
        assertThat(mainPage.getTitle()).isEqualTo("veraPDF viewer");

        then(mainPage.getDemoButton().getText()).isEqualTo("Go to demo");
        then(mainPage.getDemoButton().getAttribute("href")).isEqualTo("https://verapdf.duallab.com/demo");

        AppPage appPage = mainPage.openAppPage();
        assertThat(appPage.getTitle()).isEqualTo("veraPDF for WCAG");

        appPage.dropPDFFile(FILE_NAME);
        int compliant = appPage.navigateTo_ConfigureJob().selectProfile(PROFILE_NAME).validate();
        assertThat(compliant).isLessThan(100);

        InspectErrorsPage errorsPage = appPage.navigateTo_InspectErrorsPage();
        assertThat(errorsPage.getPDFFileName()).isEqualTo(FILE_NAME);
        assertThat(errorsPage.getListErrorsSize()).isBetween(1, 6);

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
