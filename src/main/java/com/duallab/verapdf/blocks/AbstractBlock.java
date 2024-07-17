package com.duallab.verapdf.blocks;

import com.duallab.verapdf.base.AbstractElement;

public abstract class AbstractBlock<T, P extends AbstractElement<P>> extends AbstractElement<T> {

    private final P parent;

    protected AbstractBlock(P baseElement) {
        this.parent = baseElement;
    }

    public P getParentElement() {
        return parent;
    }
}
