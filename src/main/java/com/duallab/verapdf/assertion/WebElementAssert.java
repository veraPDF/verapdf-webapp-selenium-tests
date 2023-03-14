package com.duallab.verapdf.assertion;

import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

public class WebElementAssert extends AbstractAssert<WebElementAssert, WebElement> {
    private static Logger log = Logger.getLogger(WebElementAssert.class.getName());

    protected WebElementAssert(WebElement webElement) {
        super(webElement, WebElementAssert.class);
    }

    public static WebElementAssert myAssertThat(WebElement el) {
        return new WebElementAssert(el);
    }

    public WebElementAssert hasAttribute(String attribute, String pointer) {
        isNotNull();
        return this;
    }

    public WebElementAssert hasAttributeValue(String attr, String value) {
        log.info("Checking ... Attribute: '" + attr + "' with value: " + value);
        isNotNull();
        if (!actual.getAttribute(attr).equals(value)) {
            failWithMessage("Expected element to have attribute <%s> value <%s>. But It was not ... ", attr, value);
        }
        return this;
    }

    public WebElementAssert hasCSSValue(String cssValue, String value) {
        log.info("Checking ... cssValue: '" + cssValue + "' with value: " + value);
        isNotNull();
        if (!actual.getCssValue(cssValue).equals(value)) {
            failWithMessage("Expected element to have cssValue:<%s> value:<%s>. But It was not ... ", cssValue, value);
        }
        return this;
    }

    public WebElementAssert containInCSSValue(String cssValue, String value) {
        log.info("Checking ... cssValue: '" + cssValue + "' with value: " + value);
        isNotNull();
        if (!actual.getCssValue(cssValue).equals(value)) {
            failWithMessage("Expected element to have cssValue <%s> value <%s>. But It was not ... ", cssValue, value);
        }
        return this;
    }
}

