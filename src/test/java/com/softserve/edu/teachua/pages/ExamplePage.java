package com.softserve.edu.teachua.pages;

import com.softserve.edu.util.DriverWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Reference page. New TeachUA pages should follow this shape:
 * take the shared {@link DriverWrapper}, then {@link PageFactory#initElements}.
 */
public class ExamplePage {

    private final DriverWrapper driver;

    @FindBy(id = "use-custom-filtering-algorithms")
    private WebElement customFilter;

    @FindBy(css = "div.embedded-demo:nth-child(22) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > iframe:nth-child(1)")
    private WebElement customFilterFrame;

    @FindBy(css = "th.TableFilterCell-cell:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
    private WebElement nameInput;

    @FindBy(css = "tbody tr")
    private List<WebElement> filterResult;

    public ExamplePage(DriverWrapper driver) {
        this.driver = driver;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public ExamplePage customFilter() {
        driver.scrollToElement(customFilter);
        driver.moveToFrame(customFilterFrame);
        return this;
    }

    public ExamplePage typeName(String text) {
        driver.waitAndType(nameInput, text);
        return this;
    }

    public int searchResultCount() {
        return filterResult.size();
    }
}
