package elements.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public abstract class EditableField extends BaseField {
    public EditableField(BaseField parent, By locator) {
        super(parent, locator);
    }

    public EditableField(By locator) {
        super(locator);
    }

    public void setValue(String value) {
        WebElement element = getMobileElement();
        element.clear();
        element.sendKeys(value);
    }

    public void submit() {
        getMobileElement().sendKeys(Keys.ENTER);
    }
}
