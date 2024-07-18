package com.duallab.verapdf.tests.smoke;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.duallab.verapdf.base.AbstractBaseTest;
import com.duallab.verapdf.blocks.dialog.ErrorDialog;
import com.duallab.verapdf.pages.HomePage;
import java.io.File;
import java.util.List;
import org.testng.annotations.Test;

public class Pdf4WcagSmokeTest extends AbstractBaseTest {

    private static final String SOURCE_FOLDER =
            "./src/test/resources/com/duallab/verapdf/tests/smoke/Pdf4WcagSmokeTest/";

    @Test
    public void smokeTest() {
        final String fileName = "testFile.pdf";

        new HomePage()
                .getHomeBlock()
                .goToUploadFilePage()
                .openSettingsPage(new File(SOURCE_FOLDER + fileName))
                .getSettingsBlock()
                .goToResultSummaryPage()

                // Check expected result
                .assertCondition(resultSummaryPage -> resultSummaryPage.getResultSummaryBlock().getJobFilenameElement(),
                        Condition.text(fileName))
                .assertCondition(resultSummaryPage -> resultSummaryPage.getResultSummaryBlock()
                        .getMachineAndHumanProfileNameElement(), Condition.visible)
                .assertConditions(
                        resultSummaryPage -> resultSummaryPage.getResultSummaryBlock().getSummaryListElements(),
                        CollectionCondition.exactTexts(
                                List.of("Structure: 1 error", "Page: 0 errors", "Lang: 0 errors", "Font: 0 errors",
                                        "Artifact: 0 errors", "Annotation: 0 errors", "Alt Text: 0 errors",
                                        "Text: 0 errors", "Metadata: 0 errors", "Contrast: 0 errors",
                                        "Syntax: 0 errors")))
                .assertCondition(
                        resultSummaryPage -> resultSummaryPage.getResultSummaryBlock().getSummaryResultElement(),
                        Condition.text("1020 checks passed\n1 errors"))

                .getResultSummaryBlock()
                .goToPdfViewerPage()

                // Check expected result
                .assertConditions(pdfViewerPage -> pdfViewerPage.getPdfViewerBlock().getContextTextElements(),
                        CollectionCondition.exactTexts(
                                List.of("table", "The tags do not sufficiently describe the structure of this table")))
                .assertCondition(pdfViewerPage -> pdfViewerPage.getPdfViewerBlock().getRuleItemElement(),
                        Condition.text("1"))

                .getPdfViewerBlock()
                .clickInfoButton()

                .assertCondition(ErrorDialog::getDialogTitleElement, Condition.text(
                        "7.5-1 (ISO 14289-1:2014) The tags do not sufficiently describe the structure of this table"))
                .assertCondition(ErrorDialog::getDialogDescriptionElement, Condition.text(
                        "This table lacks the correct tags to reflect its structure. This makes it difficult for "
                                + "reader software to read the table out correctly. Try reformatting the table and "
                                + "give the table a well defined header row or header column. Keep the table as "
                                + "simple as possible. Should this not solve the problem, try using another "
                                + "application (for example LibreOffice instead of Word)."))

                .clickCloseButton()
                .highlightItemError()

                // Check expected result
                .assertCondition(pdfViewerPage -> pdfViewerPage.getPdfViewerBlock().getSelectedBoxElement(),
                        Condition.cssValue("background-color", "rgba(255, 69, 0, 0.5)"));
    }
}
