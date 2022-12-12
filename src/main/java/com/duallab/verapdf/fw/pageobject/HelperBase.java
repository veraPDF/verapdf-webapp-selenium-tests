package com.duallab.verapdf.fw.pageobject;

import com.duallab.verapdf.fw.ApplicationManager;

public class HelperBase {

    protected static ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        HelperBase.manager = manager;
    }
}
