package com.duallab.verapdf.blocks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.pages.AboutPage;
import com.duallab.verapdf.pages.HomePage;
import com.duallab.verapdf.pages.PrivacyPolicyPage;
import com.duallab.verapdf.pages.UploadFilePage;
import io.qameta.allure.Step;

public final class HomeBlock extends AbstractBlock<HomeBlock, HomePage> {

    private static final SelenideElement TRY_NOW_BUTTON = Selenide.$("#try-now-button");
    private static final SelenideElement PRIVACY_POLICY_BUTTON = Selenide.$("a[href='/validate/privacy-policy']");
    private static final SelenideElement READ_MORE_BUTTON = Selenide.$("a[href='/validate/about']");

    public HomeBlock(HomePage baseElement) {
        super(baseElement);
    }

    @Step("Clicks try now button")
    public UploadFilePage goToUploadFilePage() {
        TRY_NOW_BUTTON.click();
        return UploadFilePage.openToUploadFromComputer();
    }

    @Step("Clicks privacy policy button")
    public PrivacyPolicyPage clickPrivacyPolicyButton() {
        PRIVACY_POLICY_BUTTON.click();
        return PrivacyPolicyPage.open();
    }

    @Step("Clicks read more button")
    public AboutPage clickReadMoreButton() {
        READ_MORE_BUTTON.click();
        return AboutPage.open();
    }
}
