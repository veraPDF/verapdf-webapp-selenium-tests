package com.duallab.verapdf.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.blocks.dialog.ErrorDialog;
import com.duallab.verapdf.pages.PdfViewerPage;
import io.qameta.allure.Step;

public final class PdfViewerBlock extends AbstractBlock<PdfViewerBlock, PdfViewerPage> {

    private static final SelenideElement RULE_ITEM = Selenide.$(".rule-item__chip");
    private static final SelenideElement RULE_ITEM_CONTENT = Selenide.$(".rule-item_error");
    private static final SelenideElement CHECK_ITEM = Selenide.$(".check-item");
    private static final SelenideElement SELECTED_BOX = Selenide.$("div.pdf-bbox_selected");
    private static final SelenideElement INFO_BUTTON = Selenide.$("button[aria-label='info']");
    private static final ElementsCollection CONTEXT_TEXT = Selenide.$$(".content-text");

    public PdfViewerBlock(PdfViewerPage baseElement) {
        super(baseElement);
    }

    @Step("Expands rule item content")
    public PdfViewerPage clickRuleItemContent() {
        RULE_ITEM_CONTENT.click();
        return getParentElement();
    }

    @Step("Clicks check item content")
    public PdfViewerPage clickCheckItemContent() {
        CHECK_ITEM.click();
        return getParentElement();
    }

    @Step("Clicks info button")
    public ErrorDialog clickInfoButton() {
        INFO_BUTTON.click();
        return ErrorDialog.openErrorDialog(getParentElement());
    }

    @Step("Gets rule item element")
    public SelenideElement getRuleItemElement() {
        return RULE_ITEM;
    }

    @Step("Gets content text elements")
    public ElementsCollection getContextTextElements() {
        return CONTEXT_TEXT;
    }

    @Step("Gets selected box element")
    public SelenideElement getSelectedBoxElement() {
        return SELECTED_BOX;
    }
}
