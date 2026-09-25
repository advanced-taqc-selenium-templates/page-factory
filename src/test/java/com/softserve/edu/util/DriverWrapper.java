package com.softserve.edu.util;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class DriverWrapper {

    private final ThreadLocal<WebDriver> threadDriver;
    private final ThreadLocal<Wait<WebDriver>> threadWait;
    private final Actions actions;
    private final JavascriptExecutor js;
    private final int WAIT_SEC;

    public DriverWrapper(WebDriver driver, int sec) {
        threadDriver = new ThreadLocal<>();
        threadDriver.set(driver);
        threadWait = new ThreadLocal<Wait<WebDriver>>();
        threadWait.set(new WebDriverWait(driver, Duration.ofSeconds(sec)));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        WAIT_SEC = sec;
    }

    public void scrollToElement(WebElement element) {
        threadWait.get().until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void moveToFrame(WebElement frame){
        threadWait.get().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public void waitAndType(WebElement element, String text) {
        threadWait.get().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    private WebElement visibilityElement(By locator) {
        threadWait.get().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return threadDriver.get().findElement(locator);
    }

    private WebElement clickableElement(By locator) {
        threadWait.get().until(ExpectedConditions.elementToBeClickable(locator));
        return threadDriver.get().findElement(locator);
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }
}

