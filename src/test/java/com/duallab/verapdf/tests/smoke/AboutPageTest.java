package com.duallab.verapdf.tests.smoke;

import com.codeborne.selenide.Condition;
import com.duallab.verapdf.base.AbstractBaseTest;
import com.duallab.verapdf.blocks.AboutBlock;
import com.duallab.verapdf.pages.HomePage;
import org.testng.annotations.Test;

public class AboutPageTest extends AbstractBaseTest {

    @Test
    public void aboutPageTest() {
        new HomePage()
                .getHomeBlock()
                .clickReadMoreButton()
                .getAboutBlock()

                // Check expected result
                .assertCondition(AboutBlock::getAboutHeaderElement, Condition.visible);
    }
}
