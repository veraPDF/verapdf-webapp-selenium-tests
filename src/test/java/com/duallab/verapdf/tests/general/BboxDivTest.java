package com.duallab.verapdf.tests.general;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.fw.pageobject.AppPage;
import com.duallab.verapdf.fw.pageobject.InspectErrorsPage;
import com.duallab.verapdf.fw.pageobject.MainPage;
import com.duallab.verapdf.tests.BaseTest;
import com.duallab.verapdf.tools.RetryTest;
import org.apache.log4j.Logger;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Signature;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.types.TInt32;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class BboxDivTest extends BaseTest {
    private static Logger log = Logger.getLogger(BboxDivTest.class.getName());
    private final String FILE_NAME = "test_6-3-8-t01-fail-a.pdf";
    private final String PROFILE_NAME = "PDFUA_1_MACHINE";
    private final String PROFILE_NAME2 = "WCAG_2_1_DEV"; // 100% compliant

    @Test(timeOut = 30000, retryAnalyzer = RetryTest.class)
    public void checkEndToEndValidation() throws Exception {
        
        log.info("Hello TensorFlow " + TensorFlow.version());

        try (ConcreteFunction dbl = ConcreteFunction.create(BboxDivTest::dbl);
             TInt32 x = TInt32.scalarOf(10);
             Tensor dblX = dbl.call(x)) {
            log.info(x.getInt() + " doubled is " + ((TInt32) dblX).getInt());
        }


        MainPage mainPage = new MainPage(app.getDriver()).openMainPage();
        log.info("Checking ...");

        assertThat(mainPage.getTitle()).isEqualTo("veraPDF viewer");
    }

    @BeforeMethod
    public void setup(Method method) {
        log.info("Running Test: " + method.getName());
        app = new ApplicationManager();
        log.info("Done\n\n");
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        app.stop(result);
        log.info("Done\n\n");
    }

    private static Signature dbl(Ops tf) {
        Placeholder<TInt32> x = tf.placeholder(TInt32.class);
        Add<TInt32> dblX = tf.math.add(x, x);
        return Signature.builder().input("x", x).output("dbl", dblX).build();
    }
}
