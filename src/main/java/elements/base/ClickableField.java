package elements.base;

import base.AppiumDriverUtil;
import org.openqa.selenium.By;

public abstract class ClickableField extends BaseField {
    public ClickableField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public ClickableField(By locator) {
        super(locator);
    }

    public void tap() {
        getMobileElement().click();
    }

    public void tap(int times) {
        for (int i = 0; i < times; i++) {
            tap();
            AppiumDriverUtil.sleep(500);
        }
    }
}
