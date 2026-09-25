package com.softserve.edu.teachua.tests;

import com.softserve.edu.util.DriverWrapper;
import io.github.cdimascio.dotenv.Dotenv;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ExtendWith(RunnerExtension.class)
public abstract class TestRunner {

    private static final Integer WAIT_SECONDS = 3;
    private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    protected static boolean isTestSuccessful = false;
    protected DriverWrapper driverWrapper;


    private void takeScreenShot() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);

        File scrFile = ((TakesScreenshot) driverWrapper.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

    private void takePageSource() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = driverWrapper.getDriver().getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + "_source.html.txt");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }


    @BeforeEach
    public void beforeEach() {
        Dotenv dotenv = Dotenv.load();
        if ("firefox".equalsIgnoreCase(dotenv.get("browser"))) {
            driverWrapper = new DriverWrapper(new FirefoxDriver(), WAIT_SECONDS);
        }
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        if (!isTestSuccessful) {
            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");

            takeScreenShot();
            takePageSource();
        }
        driverWrapper.getDriver().manage().deleteAllCookies();
        driverWrapper.getDriver().quit();

    }
}
