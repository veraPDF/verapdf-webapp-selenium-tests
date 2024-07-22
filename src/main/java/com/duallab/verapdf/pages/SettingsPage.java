package com.duallab.verapdf.pages;

import com.duallab.verapdf.blocks.SettingsBlock;

public final class SettingsPage extends AbstractPage<SettingsPage> {

    private final SettingsBlock settingsBlock;

    public SettingsPage() {
        this.settingsBlock = new SettingsBlock(this);
    }

    public static SettingsPage open() {
        return new SettingsPage();
    }

    public SettingsBlock getSettingsBlock() {
        return settingsBlock;
    }
}
