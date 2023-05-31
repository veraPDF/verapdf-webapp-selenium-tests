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

import static org.assertj.core.api.Assertions.assertThat;

public class OpenCVTest {

    private static final Logger log = Logger.getLogger(OpenCVTest.class);

    @Test
    public void opencvCheck() {
        OpenCV.loadLocally();
        log.info("Checking ... Core.VERSION: " + Core.VERSION);

        //Load image file
        Mat source = Imgcodecs.imread("src\\test\\java\\com\\duallab\\verapdf\\dataFiles\\" + "screen_a.png");
        Mat template = Imgcodecs.imread("src\\test\\java\\com\\duallab\\verapdf\\dataFiles\\" + "5.png");

        Mat outputImage = new Mat();
        int machMethod = Imgproc.TM_CCOEFF;

        //Template matching method
        Imgproc.matchTemplate(source, template, outputImage, machMethod);
        Imgcodecs.imwrite("target\\" + "outputImage_0_1_2.jpg", outputImage);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc = mmr.maxLoc;
        assertThat(mmr.maxLoc.x).isEqualTo(123.0);
        assertThat(mmr.maxLoc.y).isEqualTo(125.0);

        //Draw rectangle on result image
        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.width(),
                matchLoc.y + template.height()), new Scalar(200, 0, 0), 2);

        Imgcodecs.imwrite("target\\" + "screen_a_check_12.png", source);
        log.info("Complated.");
    }
}
