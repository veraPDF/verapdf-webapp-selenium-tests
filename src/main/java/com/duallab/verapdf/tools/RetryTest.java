package com.duallab.verapdf.tools;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

    private static Logger log = Logger.getLogger(RetryTest.class.getName());

    private int retryCount = 0;
    private int maxRetryCount = Integer.parseInt(PropertiesValue.getPropertiesValue("maxRetryCount"));

    public RetryTest() throws IOException {
    }

    public boolean retry(ITestResult result) {

        if (retryCount < maxRetryCount) {
            retryCount++;
            log.info("Retrying...,  retryCount: " + retryCount);
            return true;
        }
        return false;
    }
}
