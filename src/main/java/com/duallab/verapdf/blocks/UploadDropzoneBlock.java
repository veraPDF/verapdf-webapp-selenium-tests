package com.duallab.verapdf.blocks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.duallab.verapdf.pages.UploadFilePage;
import io.qameta.allure.Step;
import java.io.File;

public final class UploadDropzoneBlock extends AbstractBlock<UploadDropzoneBlock, UploadFilePage> {

    private static final SelenideElement DROPZONE = Selenide.$("input[accept='application/pdf']");

    public UploadDropzoneBlock(UploadFilePage baseElement) {
        super(baseElement);
    }

    @Step("Uploads file")
    public UploadFilePage uploadFile(File file) {
        DROPZONE.uploadFile(file);
        return getParentElement();
    }
}
