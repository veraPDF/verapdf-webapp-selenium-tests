package com.verapdf.selenium.blocks;

import com.verapdf.selenium.pages.BasePageTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends BasePageTest {

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
