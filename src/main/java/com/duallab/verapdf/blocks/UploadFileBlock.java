package com.duallab.verapdf.blocks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.pages.SettingsPage;
import com.duallab.verapdf.pages.UploadFilePage;
import io.qameta.allure.Step;

public final class UploadFileBlock extends AbstractBlock<UploadFileBlock, UploadFilePage> {

    private static final SelenideElement CONFIGURE_JOB_BUTTON = Selenide.$x("//span[text()='Configure job']");

    public UploadFileBlock(UploadFilePage baseElement) {
        super(baseElement);
    }

    @Step("Clicks configure job button")
    public SettingsPage goToSettingsPage() {
        CONFIGURE_JOB_BUTTON.click();
        return SettingsPage.open();
    }
}
