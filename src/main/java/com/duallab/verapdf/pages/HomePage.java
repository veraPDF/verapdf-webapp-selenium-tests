package com.duallab.verapdf.pages;

import com.duallab.verapdf.blocks.HomeBlock;

public final class HomePage extends AbstractPage<HomePage> {

    private final HomeBlock homeBlock;

    public HomePage() {
        this.homeBlock = new HomeBlock(this);
    }

    public HomeBlock getHomeBlock() {
        return homeBlock;
    }
}
