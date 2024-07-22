package com.duallab.verapdf.pages;

import com.duallab.verapdf.blocks.PrivacyPolicyBlock;

public final class PrivacyPolicyPage extends AbstractPage<PrivacyPolicyPage> {

    private final PrivacyPolicyBlock privacyPolicyBlock;

    public PrivacyPolicyPage() {
        this.privacyPolicyBlock = new PrivacyPolicyBlock(this);
    }

    public static PrivacyPolicyPage open() {
        return new PrivacyPolicyPage();
    }

    public PrivacyPolicyBlock getPrivacyPolicyBlock() {
        return privacyPolicyBlock;
    }
}
