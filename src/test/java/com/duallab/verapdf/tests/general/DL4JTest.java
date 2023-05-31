package com.duallab.verapdf.tests.general;

import com.duallab.verapdf.fw.ai.DataUtil;
import nu.pattern.OpenCV;
import org.apache.log4j.Logger;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DL4JTest {
    private static final int HEIGHT = 60;
    private static final int WIDTH = 60;
    private static final int N_OUTCOMES = 2;
    private static final NativeImageLoader IMAGE_LOADER = new NativeImageLoader(HEIGHT, WIDTH);
    private static final ImagePreProcessingScaler IMAGE_PRE_PROCESSING_SCALER = new ImagePreProcessingScaler(0, 1);
    private static final MultiLayerNetwork NETWORK = DataUtil.loadModel();

    private static Logger log = Logger.getLogger(DL4JTest.class.getName());
    String filePath = "src/test/java/com/duallab/verapdf/dataFiles/";

    public DL4JTest() throws IOException {
    }

    @Test
    public void predictScreenShot() throws IOException {
        OpenCV.loadLocally();

        String fileSource_r1 = filePath + "r1.png";
        Mat image_r1 = Imgcodecs.imread(fileSource_r1);
        int predictedValue_r1 = DataUtil.evaluateImage(image_r1, HEIGHT, WIDTH, N_OUTCOMES, IMAGE_LOADER, IMAGE_PRE_PROCESSING_SCALER, NETWORK);
        log.info("PredictedValue 6/r1.png: " + predictedValue_r1);
        assertThat(predictedValue_r1).isEqualTo(6);

        String fileSource_g1 = filePath + "g1.png";
        Mat image_g1 = Imgcodecs.imread(fileSource_g1);
        int predictedValue_g1 = DataUtil.evaluateImage(image_g1, HEIGHT, WIDTH, N_OUTCOMES, IMAGE_LOADER, IMAGE_PRE_PROCESSING_SCALER, NETWORK);
        log.info("PredictedValue 5/g1.png: " + predictedValue_g1);
        assertThat(predictedValue_g1).isEqualTo(5);

        String fileSource_x24 = filePath + "x24.png";
        Mat image_x24 = Imgcodecs.imread(fileSource_x24);
        int predictedValue_x24 = DataUtil.evaluateImage(image_x24, HEIGHT, WIDTH, N_OUTCOMES, IMAGE_LOADER, IMAGE_PRE_PROCESSING_SCALER, NETWORK);
        log.info("PredictedValue 2/x24.png: " + predictedValue_x24);
        assertThat(predictedValue_x24).isEqualTo(2);
    }
}
