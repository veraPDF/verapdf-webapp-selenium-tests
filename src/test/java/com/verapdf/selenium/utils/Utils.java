package com.verapdf.selenium.utils;

import java.io.File;

public class Utils {

    private Utils() {

    }

    public static String absolutePath(String path) {
        return new File(path).getAbsolutePath();
    }
}
