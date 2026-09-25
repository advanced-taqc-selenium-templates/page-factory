package com.softserve.edu.teachua.pages;

import com.softserve.edu.util.DriverWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.time.Duration;
import java.util.List;

public class ExamplePage {

    private final DriverWrapper driverWr;

    @FindBy(id = "use-custom-filtering-algorithms")
    private WebElement customFilter;

    @FindBy(css = "div.embedded-demo:nth-child(22) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > iframe:nth-child(1)")
    private WebElement customFilterFrame;

    @FindBy(css = "th.TableFilterCell-cell:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
    private WebElement nameInput;

    @FindBy(css = "tbody tr")
    private List<WebElement> filterResult;

    public ExamplePage(WebDriver driver) {
        driverWr = new DriverWrapper(driver, 3);
        PageFactory.initElements(driver, this);
    }

    public ExamplePage customFilter() {
        driverWr.scrollToElement(customFilter);
        driverWr.moveToFrame(customFilterFrame);
        return this;
    }

    public ExamplePage typeName(String text) {
        driverWr.waitAndType(nameInput, text);
        return this;
    }

    public int searchResultCount() {
        return filterResult.size();
    }
}
