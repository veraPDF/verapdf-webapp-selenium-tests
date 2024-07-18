package com.duallab.verapdf.pages;

import com.duallab.verapdf.blocks.PdfViewerBlock;

public final class PdfViewerPage extends AbstractPage<PdfViewerPage> {

    private final PdfViewerBlock pdfViewerBlock;

    public PdfViewerPage() {
        this.pdfViewerBlock = new PdfViewerBlock(this);
    }

    public static PdfViewerPage open() {
        return new PdfViewerPage();
    }

    public PdfViewerBlock getPdfViewerBlock() {
        return pdfViewerBlock;
    }

    public PdfViewerPage highlightItemError() {
        return pdfViewerBlock
                .clickRuleItemContent()
                .getPdfViewerBlock()
                .clickCheckItemContent();
    }
}
