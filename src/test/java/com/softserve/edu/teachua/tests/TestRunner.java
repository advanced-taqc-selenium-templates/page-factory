package com.softserve.edu.teachua.tests;

import com.softserve.edu.util.DriverWrapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(RunnerExtension.class)
public abstract class TestRunner {

    protected static final int WAIT_SECONDS = 10;
    private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private static final Path ARTIFACTS_DIR = Path.of("screenshots");

    static boolean isTestSuccessful;

    protected DriverWrapper driverWrapper;

    /**
     * Task 1. This method is the student starting point, not the finished design.
     * <p>
     * {@code .env} is loaded on every test, and only Firefox is created.
     * Read the environment once, and create Firefox, Chrome, or Edge from {@code browser}.
     * An unknown browser name must fail with a message that includes that name.
     */
    @BeforeEach
    public void beforeEach() {
        isTestSuccessful = false;
        Dotenv dotenv = Dotenv.load();
        if ("firefox".equalsIgnoreCase(dotenv.get("browser"))) {
            driverWrapper = new DriverWrapper(new FirefoxDriver(), WAIT_SECONDS);
        }
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        WebDriver driver = driverWrapper == null ? null : driverWrapper.getDriver();
        if (driver == null) {
            return;
        }
        if (!isTestSuccessful) {
            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");
            takeScreenShot(driver);
            takePageSource(driver);
        }
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    private void takeScreenShot(WebDriver driver) {
        String currentTime = timestamp();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(ARTIFACTS_DIR);
            FileUtils.copyFile(scrFile, ARTIFACTS_DIR.resolve(currentTime + "_screenshot.png").toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void takePageSource(WebDriver driver) {
        String currentTime = timestamp();
        Path path = ARTIFACTS_DIR.resolve(currentTime + "_source.html");
        try {
            Files.createDirectories(ARTIFACTS_DIR);
            Files.writeString(path, driver.getPageSource());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_TEMPLATE));
    }
}
