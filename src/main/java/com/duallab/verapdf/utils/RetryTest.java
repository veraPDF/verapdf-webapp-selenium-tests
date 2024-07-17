package com.duallab.verapdf.utils;

import com.duallab.verapdf.base.AbstractPdf4WcagSelenideConfig;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public final class RetryTest implements IRetryAnalyzer {

    private int retryCount = 0;

    public boolean retry(ITestResult result) {
        if (retryCount < Integer.parseInt(AbstractPdf4WcagSelenideConfig.getInitializedProperty("maxRetryCount"))) {
            retryCount++;
            return true;
        }
        return false;
    }
}
