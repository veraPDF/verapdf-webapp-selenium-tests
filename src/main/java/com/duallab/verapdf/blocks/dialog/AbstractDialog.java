package com.duallab.verapdf.blocks.dialog;

import com.duallab.verapdf.base.AbstractElement;
import com.duallab.verapdf.blocks.AbstractBlock;

public abstract class AbstractDialog<T, P extends AbstractElement<P>> extends AbstractBlock<T, P> {

    protected AbstractDialog(P baseElement) {
        super(baseElement);
    }
}
