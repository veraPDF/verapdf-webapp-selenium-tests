package com.duallab.verapdf.tools;

import java.io.File;

import org.apache.log4j.Logger;

public class LatestFileInDir {
    private static Logger log = Logger.getLogger(LatestFileInDir.class.getName());

    public static File latestFileInDir(File dir) {
        File[] files = dir.listFiles(File::isFile);
        if (files == null || files.length == 0) {
            return null;
        }
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
            }
        }
        return choice;
    }

    public static File getLatestFileInDirToCheck() {
        String userHomeDownloads = System.getProperty("user.home") + "\\Downloads";

        log.info("UserHomeDownloads : " + userHomeDownloads + "\n");
        File xmlFileToCheck = LatestFileInDir.latestFileInDir(new File(userHomeDownloads));
        log.info("LatestFileInDir : " + xmlFileToCheck.getName() + "\n");
        return xmlFileToCheck;
    }
}
