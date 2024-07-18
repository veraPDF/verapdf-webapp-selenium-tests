package com.duallab.verapdf.blocks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.pages.PrivacyPolicyPage;
import io.qameta.allure.Step;

public final class PrivacyPolicyBlock extends AbstractBlock<PrivacyPolicyBlock, PrivacyPolicyPage> {

    private static final SelenideElement PRIVACY_POLICY_HEADER = Selenide.$("#privacy-policy");

    public PrivacyPolicyBlock(PrivacyPolicyPage baseElement) {
        super(baseElement);
    }

    @Step("Gets privacy policy element")
    public SelenideElement getPrivacyPolicyHeaderElement() {
        Selenide.switchTo().window(1);
        return PRIVACY_POLICY_HEADER;
    }
}
