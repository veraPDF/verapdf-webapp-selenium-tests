package com.verapdf.selenium.test.settings;

import com.verapdf.selenium.pages.BasePageTest;
import org.testng.annotations.Test;

public class Profiles extends BasePageTest {

    @Test
    public void selectWcagCompleteProfile() {
        homePage.switchToUploadFile();
        uploadPage.uploadPdf();
    }

    @Test
    public void selectWcag21Profile() {

    }

    @Test
    public void selectPdfUaMachineProfile() {

    }

    @Test
    public void selectPdfUaHumanProfile() {

    }
}
