package com.duallab.verapdf.tests.general;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.fw.pageobject.AppPage;
import com.duallab.verapdf.fw.pageobject.InspectErrorsPage;
import com.duallab.verapdf.fw.pageobject.MainPage;
import com.duallab.verapdf.tests.BaseTest;
import com.duallab.verapdf.tools.RetryTest;
import nu.pattern.OpenCV;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class OpenCVTest extends BaseTest {
    private final String FILE_NAME = "test_6-3-8-t01-fail-a.pdf";
    private final String PROFILE_NAME = "PDFUA_1_MACHINE";
    String filePath = "target/";
    private static final Logger log = Logger.getLogger(OpenCVTest.class);

    @Test (timeOut = 30000, retryAnalyzer = RetryTest.class)
    public void opencvCheck() throws IOException {
        OpenCV.loadLocally();
        log.info("Checking ... Core.VERSION: " + Core.VERSION);

        MainPage mainPage = new MainPage(app.getDriver()).openMainPage();
        log.info("Checking ...");
        assertThat(mainPage.getTitle()).isEqualTo("veraPDF viewer");

        then(mainPage.getDemoButton().getText()).isEqualTo("Go to demo");
        then(mainPage.getDemoButton().getAttribute("href")).isEqualTo("https://verapdf.duallab.com/demo");

        AppPage appPage = mainPage.openAppPage();
        assertThat(appPage.getTitle()).isEqualTo("veraPDF for WCAG");

        appPage.dropPDFFile(FILE_NAME);
        appPage.navigateTo_ConfigureJob().selectProfile(PROFILE_NAME).validate();
        InspectErrorsPage errorsPage = appPage.navigateTo_InspectErrorsPage();

        File SrcFile = getDriver().findElement(By.xpath("//canvas")).getScreenshotAs(OutputType.FILE);
        String canvas_file = "canvas_" + currentDate() + ".png";
        FileUtils.copyFile(SrcFile, new File(filePath + canvas_file));


        //Load image file
        String fileSource = filePath + canvas_file;
        log.info("fileSource exists: " +  Files.exists(Paths.get(fileSource)));
        Mat source = Imgcodecs.imread(fileSource);

        log.info("Checking ... source: " + source.size());
        String fileTemplate = "src/test/java/com/duallab/verapdf/dataFiles/" + "5.png";
        log.info("fileTemplate exists: " +  Files.exists(Paths.get(fileTemplate)));
        Mat template = Imgcodecs.imread(fileTemplate);
        log.info("Checking ... template: " + template.size());


        Mat outputImage = new Mat();
        int machMethod = Imgproc.TM_CCOEFF;

        //Template matching method
        log.info("Checking ... matchTemplate... with machMethod: " + Imgproc.TM_CCOEFF);
        Imgproc.matchTemplate(source, template, outputImage, machMethod);
        Imgcodecs.imwrite("target/" + "outputImage_" + currentDate() + ".png", outputImage);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc = mmr.maxLoc;

        log.info("Checking ... mmr.maxLoc: " + mmr.maxLoc);
        assertThat(mmr.maxLoc.x).isEqualTo(123.0);
        assertThat(mmr.maxLoc.y).isEqualTo(125.0);


        //Draw rectangle on result image
        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.width(),
                matchLoc.y + template.height()), new Scalar(200, 0, 0), 2);
        log.info("Writing an image with rectangle to a target folder ... ");
        Imgcodecs.imwrite("target/" + "canvas_with_rectangle" + currentDate() + ".png", source);
        log.info("Completed.");
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
}
