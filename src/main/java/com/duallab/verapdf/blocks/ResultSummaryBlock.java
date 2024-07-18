package com.duallab.verapdf.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.pages.PdfViewerPage;
import com.duallab.verapdf.pages.ResultSummaryPage;
import io.qameta.allure.Step;

public final class ResultSummaryBlock extends AbstractBlock<ResultSummaryBlock, ResultSummaryPage> {

    private static final SelenideElement JOB_FILENAME = Selenide.$(".job-filename");
    private static final SelenideElement MACHINE_AND_HUMAN_PROFILE_NAME =
            Selenide.$x("//p[text()='WCAG 2.2 Machine & Human (experimental)']");
    private static final SelenideElement SUMMARY_RESULT = Selenide.$(".legend");
    private static final SelenideElement INSPECT_ERRORS_BUTTON = Selenide.$x("//span[text()='Inspect errors']");
    private static final ElementsCollection SUMMARY_LIST = Selenide.$$(".summary__list ul li");

    public ResultSummaryBlock(ResultSummaryPage baseElement) {
        super(baseElement);
    }

    @Step("Clicks inspect errors button")
    public PdfViewerPage goToPdfViewerPage() {
        INSPECT_ERRORS_BUTTON.click();
        return PdfViewerPage.open();
    }

    @Step("Gets job file name element")
    public SelenideElement getJobFilenameElement() {
        return JOB_FILENAME;
    }

    @Step("Gets profile name element")
    public SelenideElement getMachineAndHumanProfileNameElement() {
        return MACHINE_AND_HUMAN_PROFILE_NAME;
    }

    @Step("Gets summary list elements")
    public ElementsCollection getSummaryListElements() {
        return SUMMARY_LIST;
    }

    @Step("Gets summary result element")
    public SelenideElement getSummaryResultElement() {
        return SUMMARY_RESULT;
    }
}
