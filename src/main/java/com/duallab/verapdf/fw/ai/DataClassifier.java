package com.duallab.verapdf.fw.ai;

import com.duallab.verapdf.tools.PropertiesValue;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataClassifier {
    private static final String RESOURCES_FOLDER_PATH = "./resources/";
    private static final int HEIGHT = 60;
    private static final int WIDTH = 60;
    private static final int N_SAMPLES_TRAINING = 72; // Adjust according to the number of images in training set
    private static final int N_SAMPLES_TESTING = 7; // Adjust according to the number of images in testing set
    private static final int N_OUTCOMES = 8;

    public static void main(final String args[]) throws IOException {
        DataSetIterator dataSetIterator = getDataSetIterator(RESOURCES_FOLDER_PATH + "data/training", N_SAMPLES_TRAINING);
        buildModel(dataSetIterator);
    }

    private static DataSetIterator getDataSetIterator(final String folderPath, final int nSamples) throws IOException {
        final File folder = new File(folderPath);
        final File[] digitFolders = folder.listFiles();

        final NativeImageLoader nil = new NativeImageLoader(HEIGHT, WIDTH);
        final ImagePreProcessingScaler scaler = new ImagePreProcessingScaler(0, 1);

        INDArray input = Nd4j.create(nSamples, HEIGHT * WIDTH * 3);
        INDArray output = Nd4j.create(nSamples, N_OUTCOMES);

        int n = 0;
        for (final File digitFolder : digitFolders) {
            int labelDigit = Integer.parseInt(digitFolder.getName());
            final File[] imageFiles = digitFolder.listFiles();

            for (final File imgFile : imageFiles) {
                final INDArray img = nil.asRowVector(imgFile);
                scaler.transform(img);
                input.putRow(n, img);
                output.put(n, labelDigit, 1.0);
                n++;
            }
        }

        // Joining input and output matrices into a dataset
        final DataSet dataSet = new DataSet(input, output);
        // Convert the dataset into a list
        final List<DataSet> listDataSet = dataSet.asList();
        // Shuffle content of list randomly
        Collections.shuffle(listDataSet, new Random(System.currentTimeMillis()));

        // Build and return a dataset iterator
        return new ListDataSetIterator<>(listDataSet, 10);
    }

    private static void buildModel(DataSetIterator dsi) throws IOException {
        //https://medium.com/mlearning-ai/deeplearning4-for-image-classification-part-1-fc01cb2b1c62
        final MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .updater(new Nesterovs(0.0015, 0.98))
                .weightInit(WeightInit.XAVIER)
                .list()
                .layer(0, new DenseLayer.Builder()
                        .activation(Activation.RELU)
                        .nIn(HEIGHT * WIDTH * 3)
                        .nOut(1000)
                        .build())
                .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(1000)
                        .nOut(N_OUTCOMES)
                        .build())
                .build();

        final MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(50));
        model.fit(dsi, 25);

        //Evaluation
        DataSetIterator testDsi = getDataSetIterator(RESOURCES_FOLDER_PATH + "data/testing", N_SAMPLES_TESTING);
        Evaluation eval = model.evaluate(testDsi);
        System.out.print(eval.stats());

        ModelSerializer.writeModel(model, PropertiesValue.getPropertiesValue("model"), true);
    }
}
