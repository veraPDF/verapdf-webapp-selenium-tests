package com.duallab.verapdf.tools;

import org.apache.log4j.Logger;

public class Credentials {
    public static String userName = PropertiesValue.getPropertiesValue("userName");
    public static String userPassWord = PropertiesValue.getPropertiesValue("passWord");
    public static String conText = getContext(PropertiesValue.getPropertiesValue("context"));
    private static Logger log = Logger.getLogger(Credentials.class.getName());

    private static String getContext(String context) {
        if (context.equals("INTEGR8TOR")) {
            return "(//*/ion-select-popover//*[@role='radio'])[1]";
        } else {
            return "Nothing";
        }
    }
}
