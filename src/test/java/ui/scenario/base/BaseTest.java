package ui.scenario.base;

import ui.factory.DriverFactory;

import java.net.MalformedURLException;

public class BaseTest {

    public void setUp(String browser, String executionType) throws MalformedURLException {

        DriverFactory.createDriver(browser, executionType);
    }
}

