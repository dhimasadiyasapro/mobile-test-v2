package elements;

import elements.base.BaseField;
import elements.base.EditableField;
import org.openqa.selenium.By;

public class TextView extends EditableField {
    public TextView(BaseField parent, By locator) {
        super(parent, locator);
    }

    public TextView(By locator) {
        super(locator);
    }
}
