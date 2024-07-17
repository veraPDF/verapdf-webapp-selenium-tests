package com.duallab.verapdf.base;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import com.codeborne.selenide.WebElementsCondition;

public abstract class AbstractElement<T> {

    protected AbstractElement() {
        // empty constructor
    }

    public T assertCondition(FunctionWithThrowing<T, SelenideElement, ? extends RuntimeException> toCheck,
            WebElementCondition... condition) {
        return assertResult(toCheck, selenideElement -> selenideElement.should(condition));
    }

    public T assertConditions(FunctionWithThrowing<T, ElementsCollection, ? extends RuntimeException> toCheck,
            WebElementsCondition... condition) {
        return assertResult(toCheck, elementsCollection -> elementsCollection.should(condition));
    }

    public <R, E extends Throwable> T assertResult(FunctionWithThrowing<T, R, E> toCheck,
            ConsumerWithThrowing<R, E> checkFunction) throws E {
        T thisAsT = (T) this;
        checkFunction.accept(toCheck.apply(thisAsT));
        return thisAsT;
    }

    public <R, E extends Throwable> R applyShortcut(FunctionWithThrowing<T, R, E> shortcut) throws E {
        T thisAsT = (T) this;
        return shortcut.apply(thisAsT);
    }

    @FunctionalInterface
    public interface ConsumerWithThrowing<T, E extends Throwable> {
        void accept(T t) throws E;
    }

    @FunctionalInterface
    public interface FunctionWithThrowing<T, R, E extends Throwable> {
        R apply(T t) throws E;
    }
}
