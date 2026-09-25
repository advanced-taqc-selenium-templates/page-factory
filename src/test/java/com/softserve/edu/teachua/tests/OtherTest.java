package com.softserve.edu.teachua.tests;

import com.softserve.edu.teachua.pages.ExamplePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtherTest extends TestRunner {

    @Test
    void testCustomFilterShowsTwoRows() {
        driverWrapper.getDriver().get("https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering/");
        ExamplePage examplePage = new ExamplePage(driverWrapper);
        examplePage.customFilter().typeName("au");
        assertEquals(2, examplePage.searchResultCount());
    }
}
