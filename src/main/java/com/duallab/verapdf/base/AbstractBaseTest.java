package com.duallab.verapdf.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public abstract class AbstractBaseTest extends AbstractPdf4WcagSelenideConfig {

    private static final String ALLURE = "Allure";

    private static final String PDF4WCAG_PROPERTIES = "./src/main/resources/pdf4wcag.properties";

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        AbstractPdf4WcagSelenideConfig.setupStaticContext(PDF4WCAG_PROPERTIES, "pdf4wcag.site.url");
    }

    @BeforeMethod(alwaysRun = true)
    public void begin() {
        retryWithCleanMechanism(() -> {
            SelenideLogger.addListener(ALLURE, new AllureSelenide());
            Selenide.open("/");
        }, this::tearDown, 3, 1000);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        SelenideLogger.removeListener(ALLURE);
        Selenide.closeWebDriver();
    }
}
