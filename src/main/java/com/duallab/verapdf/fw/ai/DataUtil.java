package com.duallab.verapdf.fw.ai;

import com.duallab.verapdf.tools.PropertiesValue;
import org.apache.log4j.Logger;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.BooleanIndexing;
import org.nd4j.linalg.indexing.conditions.Conditions;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;

public class DataUtil {
    private static Logger log = Logger.getLogger(DataUtil.class.getName());
    public static int evaluateImage(final Mat image, int HEIGHT, int WIDTH, int N_OUTCOMES,
                                    NativeImageLoader IMAGE_LOADER,
                                    ImagePreProcessingScaler IMAGE_PRE_PROCESSING_SCALER,
                                    final MultiLayerNetwork model) throws IOException {

        try (final INDArray input = Nd4j.create(1, HEIGHT * WIDTH * 3)) {
            final INDArray imageArray = IMAGE_LOADER.asRowVector(image);
            IMAGE_PRE_PROCESSING_SCALER.transform(imageArray);
            input.putRow(0, imageArray);

            // Joining input and output matrices into a dataset
            final DataSet dataSet = new DataSet(input, Nd4j.create(1, N_OUTCOMES));

            // Data set contains one value only
            final INDArray predicted = model.output(
                    dataSet.get(0).getFeatures(),
                    false
            );
            final INDArray predictedValue = BooleanIndexing.firstIndex(
                    predicted,
                    Conditions.equals(predicted.maxNumber())
            );

            log.info("  predictedValue(maxNumber): " + predicted.maxNumber().toString());
            return Integer.parseInt(predictedValue.toString());
        }
    }

    public static MultiLayerNetwork loadModel() {
        try {
            return ModelSerializer.restoreMultiLayerNetwork(PropertiesValue.getPropertiesValue("model"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
