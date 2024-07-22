package com.duallab.verapdf.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.DefaultAnnotationTransformer;

public final class Pdf4WcagAutoRetryTransformer extends DefaultAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryTest.class);
        super.transform(annotation, testClass, testConstructor, testMethod);
    }
}
