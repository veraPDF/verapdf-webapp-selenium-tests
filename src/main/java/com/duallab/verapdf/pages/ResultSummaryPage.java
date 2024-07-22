package com.duallab.verapdf.pages;

import com.duallab.verapdf.blocks.ResultSummaryBlock;

public final class ResultSummaryPage extends AbstractPage<ResultSummaryPage> {

    private final ResultSummaryBlock resultSummaryBlock;

    public ResultSummaryPage() {
        this.resultSummaryBlock = new ResultSummaryBlock(this);
    }

    public static ResultSummaryPage open() {
        return new ResultSummaryPage();
    }

    public ResultSummaryBlock getResultSummaryBlock() {
        return resultSummaryBlock;
    }
}
