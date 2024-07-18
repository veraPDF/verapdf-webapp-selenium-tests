package com.duallab.verapdf.pages;

import com.duallab.verapdf.blocks.UploadDropzoneBlock;
import com.duallab.verapdf.blocks.UploadFileBlock;
import java.io.File;
import java.util.Objects;

public final class UploadFilePage extends AbstractPage<UploadFilePage> {

    private final UploadFileBlock uploadFileBlock;
    private final UploadDropzoneBlock uploadDropzoneBlock;

    public UploadFilePage() {
        this.uploadFileBlock = new UploadFileBlock(this);
        this.uploadDropzoneBlock = new UploadDropzoneBlock(this);
    }

    public static UploadFilePage openToUploadFromComputer() {
        return new UploadFilePage();
    }

    public UploadFileBlock getUploadFileBlock() {
        return uploadFileBlock;
    }

    public UploadDropzoneBlock getUploadDropzoneBlock() {
        return uploadDropzoneBlock;
    }

    public SettingsPage openSettingsPage(File file) {
        return Objects.requireNonNull(uploadDropzoneBlock)
                .uploadFile(file)
                .getUploadFileBlock()
                .goToSettingsPage();
    }
}
