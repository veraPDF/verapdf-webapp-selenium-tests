package com.duallab.verapdf.pages;

import com.duallab.verapdf.blocks.AboutBlock;

public final class AboutPage extends AbstractPage<AboutPage> {

    private final AboutBlock aboutBlock;

    public AboutPage() {
        this.aboutBlock = new AboutBlock(this);
    }

    public static AboutPage open() {
        return new AboutPage();
    }

    public AboutBlock getAboutBlock() {
        return aboutBlock;
    }
}
