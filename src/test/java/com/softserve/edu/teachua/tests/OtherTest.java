package com.softserve.edu.teachua.tests;

import com.softserve.edu.teachua.pages.ExamplePage;
import com.softserve.edu.util.DriverWrapper;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OtherTest extends TestRunner {

    @Test
    void test2(){
        driverWrapper.getDriver().get("https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering/");
        ExamplePage examplePage = new ExamplePage(driverWrapper.getDriver());
        examplePage.customFilter().typeName("au");
        assertEquals(2, examplePage.searchResultCount());
    }
}
