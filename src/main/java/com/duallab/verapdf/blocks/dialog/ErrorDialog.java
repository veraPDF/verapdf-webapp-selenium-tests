package com.duallab.verapdf.blocks.dialog;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.pages.PdfViewerPage;
import io.qameta.allure.Step;

public final class ErrorDialog extends AbstractDialog<ErrorDialog, PdfViewerPage> {

    private static final SelenideElement DIALOG_TITLE = Selenide.$("#alert-dialog-title");
    private static final SelenideElement DIALOG_DESCRIPTION = Selenide.$("#alert-dialog-description");
    private static final SelenideElement CLOSE_BUTTON = Selenide.$("div[title='Close']");

    private ErrorDialog(PdfViewerPage baseElement) {
        super(baseElement);
    }

    public static ErrorDialog openErrorDialog(PdfViewerPage pdfViewerPage) {
        return new ErrorDialog(pdfViewerPage);
    }

    @Step("Clicks close button")
    public PdfViewerPage clickCloseButton() {
        CLOSE_BUTTON.click();
        return getParentElement();
    }

    @Step("Gets dialog title element")
    public SelenideElement getDialogTitleElement() {
        return DIALOG_TITLE;
    }

    @Step("Gets dialog description element")
    public SelenideElement getDialogDescriptionElement() {
        return DIALOG_DESCRIPTION;
    }
}
