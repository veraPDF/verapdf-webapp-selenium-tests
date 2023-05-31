package com.duallab.verapdf.tests.general;

import nu.pattern.OpenCV;
import org.apache.log4j.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenCVTest {

    private static final Logger log = Logger.getLogger(OpenCVTest.class);

    @Test
    public void opencvCheck() {
        OpenCV.loadLocally();
        log.info("Checking ... Core.VERSION: " + Core.VERSION);

        //Load image file

        String fileSource = "src/test/java/com/duallab/verapdf/dataFiles/" + "screen_a.png";
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
        Imgcodecs.imwrite("target/" + "outputImage_0_1_2.jpg", outputImage);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc = mmr.maxLoc;

        log.info("Checking ... mmr.maxLoc: " + mmr.maxLoc);
        assertThat(mmr.maxLoc.x).isEqualTo(123.0);
        assertThat(mmr.maxLoc.y).isEqualTo(125.0);


        //Draw rectangle on result image
        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.width(),
                matchLoc.y + template.height()), new Scalar(200, 0, 0), 2);
        log.info("Writing an image with rectangle to a target folder ... ");
        Imgcodecs.imwrite("target/" + "screen_a_check_12.png", source);
        log.info("Completed.");
    }
}
