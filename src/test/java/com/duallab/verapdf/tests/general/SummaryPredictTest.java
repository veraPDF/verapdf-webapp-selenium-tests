package com.duallab.verapdf.tests.general;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.fw.ai.DataUtil;
import com.duallab.verapdf.fw.pageobject.AppPage;
import com.duallab.verapdf.fw.pageobject.MainPage;
import com.duallab.verapdf.tests.BaseTest;
import com.duallab.verapdf.tools.LogAnalysis;
import com.duallab.verapdf.tools.RetryTest;
import nu.pattern.OpenCV;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;


public class SummaryPredictTest extends BaseTest {
    private final String FILE_NAME = "test_6-3-8-t01-fail-a.pdf";
    private final String PROFILE_NAME = "PDFUA_1_MACHINE";
    private static final int HEIGHT = 60;
    private static final int WIDTH = 60;
    private static final int N_OUTCOMES = 8;
    private static final NativeImageLoader IMAGE_LOADER = new NativeImageLoader(HEIGHT, WIDTH);
    private static final ImagePreProcessingScaler IMAGE_PRE_PROCESSING_SCALER = new ImagePreProcessingScaler(0, 1);
    private static final MultiLayerNetwork NETWORK = DataUtil.loadModel();

    String filePath = "target/";
    private static Logger log = Logger.getLogger(SummaryPredictTest.class.getName());

    @DataProvider(name = "multiData")
    public static Object[][] inputData() {
        return new Object[][]{
                {"test_6-3-8-t01-fail-a.pdf"},
                {"GHOSTSCRIPT-691872-0.pdf"},
                {"GHOSTSCRIPT-695986-3.pdf"},
                {"GHOSTSCRIPT-700987-0.pdf"},
                {"GHOSTSCRIPT-701789-0.pdf"},
                {"TIKA-3031-0.pdf"},
                {"test-utf8.pdf"},
        };
    }


    @Test(timeOut = 30000, dataProvider = "multiData", retryAnalyzer = RetryTest.class)
    public void predictSummaryWebElement(String file_name) throws IOException, InterruptedException {

        MainPage mainPage = new MainPage(app.getDriver()).openMainPage();
        log.info("Checking ...");
        assertThat(mainPage.getTitle()).isEqualTo("veraPDF viewer");

        then(mainPage.getDemoButton().getText()).isEqualTo("Go to demo");
        then(mainPage.getDemoButton().getAttribute("href")).isEqualTo("https://verapdf.duallab.com/demo");

        AppPage appPage = mainPage.openAppPage();
        assertThat(appPage.getTitle()).isEqualTo("veraPDF for WCAG");

        appPage.dropPDFFile(file_name);
        appPage.navigateTo_ConfigureJob().selectProfile(PROFILE_NAME).validate();

        TimeUnit.SECONDS.sleep(3);
        File SrcFile = getDriver().findElement(By.xpath("(//*[@id='reactgooglegraph-1']/div/div[1]/div//*)[6]")).getScreenshotAs(OutputType.FILE);
        String summary_file = file_name + "summaryWebElement_" + currentDate() + ".png";
        FileUtils.copyFile(SrcFile, new File(filePath + summary_file));

        Mat image_r1 = Imgcodecs.imread(filePath + summary_file);

        int predictedValue_r1 = DataUtil.evaluateImage(image_r1, HEIGHT, WIDTH, N_OUTCOMES, IMAGE_LOADER, IMAGE_PRE_PROCESSING_SCALER, NETWORK);
        log.info("PredictedValue 7/summaryWebElement: " + predictedValue_r1);
        assertThat(predictedValue_r1).isEqualTo(7);
    }

    @BeforeMethod
    public void setup(Method method) {
        log.info("Running Test: " + method.getName());
        app = new ApplicationManager();
        log.info("Done\n\n");
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        app.stop(result);
        log.info("Done\n\n");
    }

    @BeforeClass
    public void class_setup() {
        log.info("Running class setup: " + SummaryPredictTest.class.getName());
        OpenCV.loadLocally();
        log.info("Checking OpenCV ... Core.VERSION: " + Core.VERSION);
        log.info("Done\n\n");
    }
}
