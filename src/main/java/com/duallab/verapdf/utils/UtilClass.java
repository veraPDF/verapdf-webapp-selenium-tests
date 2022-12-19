package com.duallab.verapdf.utils;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class UtilClass {
    private static Logger log = Logger.getLogger(UtilClass.class.getName());
    public static String absolutePath(String path) {
        return new File(path).getAbsolutePath();
    }

    public static CharSequence canonicalPath(String fileName) throws IOException {
        String datafiles =
                (new java.io.File(".")).getCanonicalPath()
                        + "/src/test/java/com/duallab/verapdf/dataFiles/" + fileName;
        return datafiles;
    }

    public static CharSequence canonicalPathForImages(String fileName) throws IOException {
        String imagePatterns =
                (new java.io.File(".")).getCanonicalPath()
                        + "/src/test/java/com/duallab/verapdf/dataFiles/imagePatterns/"
                        + fileName;
        return imagePatterns;
    }

    public static void saveExtractedImages(List<BufferedImage> images) throws IOException {
        log.info("Trying to save images ... count: " + images.size() );
        int i = 1;
        File dir = new File(PropertiesManager.getProperties().getProperty("extractedImagesLocation"));
        if (!dir.exists()) {
            dir.mkdir();
        }
        log.info("Saving images to ... " + dir.toString() );
        for (BufferedImage image : images) {
            String fileName = "image" + i;
            File file =
                    new File(
                            PropertiesManager.getProperties().getProperty("extractedImagesLocation") + fileName
                                    + ".png");
            ImageIO.write(image, "png", file);
            i++;
        }
    }
}
