package com.duallab.verapdf.tests.smoke;

import com.codeborne.selenide.Condition;
import com.duallab.verapdf.base.AbstractBaseTest;
import com.duallab.verapdf.blocks.PrivacyPolicyBlock;
import com.duallab.verapdf.pages.HomePage;
import org.testng.annotations.Test;

public class PrivacyPolicyPageTest extends AbstractBaseTest {

    @Test
    public void privacyPolicyPageTest() {
        new HomePage()
                .getHomeBlock()
                .clickPrivacyPolicyButton()
                .getPrivacyPolicyBlock()

                // Check expected result
                .assertCondition(PrivacyPolicyBlock::getPrivacyPolicyHeaderElement, Condition.visible);
    }
}
