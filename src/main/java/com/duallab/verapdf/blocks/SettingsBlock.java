package com.duallab.verapdf.blocks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.pages.ResultSummaryPage;
import com.duallab.verapdf.pages.SettingsPage;
import io.qameta.allure.Step;

public final class SettingsBlock extends AbstractBlock<SettingsBlock, SettingsPage> {

    private static final SelenideElement VALIDATE_BUTTON = Selenide.$x("//span[text()='Validate']");

    public SettingsBlock(SettingsPage baseElement) {
        super(baseElement);
    }

    @Step("Clicks validate button")
    public ResultSummaryPage goToResultSummaryPage() {
        VALIDATE_BUTTON.click();
        return ResultSummaryPage.open();
    }
}
