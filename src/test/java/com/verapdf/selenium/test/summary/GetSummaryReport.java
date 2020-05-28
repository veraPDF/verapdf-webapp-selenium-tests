package com.verapdf.selenium.test.summary;

import com.verapdf.selenium.pages.BasePageTest;
import org.testng.annotations.Test;


public class GetSummaryReport extends BasePageTest {
    private static final String FOLDER = "src/test/resources/com/verapdf/selenium/test/home/UploadFilePage/";
    private static final String PATH_FILE = FOLDER + "7.2-t05_fail.pdf";


    @Test
    public void summaryOfWcag21All() {
        homePage.switchToUploadFile();
        uploadJob.uploadPdf(PATH_FILE);
    }
}
