package com.softserve.edu.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Shared browser helper. One instance belongs to one test and is passed into page objects.
 * <p>
 * Task 2: pages and tests should call this type instead of using {@link WebDriver} directly.
 * Add the missing delegating methods (open URL, find, frame switch, cookies, quit) here.
 * <p>
 * Task 5: {@link #selectByVisibleText(WebElement, String...)} is the hook for
 * Bootstrap single and multiple selects. It is not implemented yet.
 */
public class DriverWrapper {

    private final WebDriver driver;
    private final Wait<WebDriver> wait;
    private final Actions actions;
    private final JavascriptExecutor js;

    public DriverWrapper(WebDriver driver, int seconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public void scrollToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void moveToFrame(WebElement frame) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public void waitAndType(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Task 5. Select one or more options by visible text.
     * A single-select replaces the current option. A multiple-select adds each given option.
     */
    public void selectByVisibleText(WebElement selectElement, String... options) {
        throw new UnsupportedOperationException("Task 5: implement select handling in DriverWrapper");
    }

    public WebElement visibleElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public WebElement clickableElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Actions actions() {
        return actions;
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
}
