package com.duallab.verapdf.blocks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.pages.AboutPage;
import io.qameta.allure.Step;

public final class AboutBlock extends AbstractBlock<AboutBlock, AboutPage> {

    private static final SelenideElement ABOUT_HEADER = Selenide.$("#about");

    public AboutBlock(AboutPage baseElement) {
        super(baseElement);
    }

    @Step("Gets about header element")
    public SelenideElement getAboutHeaderElement() {
        return ABOUT_HEADER;
    }
}
