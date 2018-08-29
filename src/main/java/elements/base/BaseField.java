package elements.base;

import base.AppiumDriverUtil;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public abstract class BaseField {
    protected final By locator;
    private final BaseField parent;

    public BaseField(By locator) {
        this(null, locator);
    }

    public BaseField(BaseField parent, By locator) {
        this.parent = parent;
        this.locator = locator;
    }

    public BaseField getParent() {
        return parent;
    }

    public MobileElement getMobileElement() {
        if (getParent() != null) {
            return AppiumDriverUtil.getElement(getParent().getMobileElement(), locator);
        } else {
            return AppiumDriverUtil.getElement(locator);
        }
    }

    public List<MobileElement> getMobileElements() {
        if (getParent() != null) {
            return AppiumDriverUtil.getElements(getParent().getMobileElement(), locator);
        } else {
            return AppiumDriverUtil.getElements(locator);
        }
    }

    public Boolean isDisplayedWithWait() {
        if (getParent() != null) {
            return AppiumDriverUtil.getElements(getParent().getMobileElement(), locator).size() > 0;
        } else {
            return AppiumDriverUtil.getElements(locator).size() > 0;
        }
    }

    public Boolean isDisplayed() {
        if (getParent() != null) {
            return AppiumDriverUtil.getElementsOrEmpty(getParent().getMobileElement(), locator).size() > 0;
        } else {
            return AppiumDriverUtil.getElementsOrEmpty(locator).size() > 0;
        }
    }

    public String getValue() {
        return getMobileElement().getText();
    }

    public String getAttribute(String attribute) {
        return getMobileElement().getAttribute(attribute);
    }
}
