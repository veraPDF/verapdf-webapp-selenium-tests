package com.verapdf.selenium.test.uploadpage;

import com.verapdf.selenium.pages.BasePageTest;
import org.testng.annotations.Test;


public class UploadFile extends BasePageTest {
    private static final String FOLDER = "src/test/resources/com/verapdf/selenium/test/uploadpage/UploadFile/";
    private static final String PATH_FILE = FOLDER + "testFile.pdf";


    @Test
    public void uploadPdf() {
        homePage.switchToUploadFile();
        uploadPage.uploadPdf(PATH_FILE);
    }
}
