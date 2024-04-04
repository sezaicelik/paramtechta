package ui.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private DriverFactory() {
    }

    private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static final String INCOGNITO = "--incognito";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    private static final String REMOTE_URL = "http://localhost:4444/wd/hub";

    public static RemoteWebDriver getDriver() {
        return driver.get();
    }

    @Step("Create Driver")
    public static RemoteWebDriver createDriver(String browser, String executionType) throws MalformedURLException {

        if (executionType.equals("grid")) return createRemoteDriver(browser);
        else return createLocalDriver(browser);
    }

    @Step("Create Local Driver")
    private static RemoteWebDriver createLocalDriver(String browserName) {

        switch (browserName) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments(INCOGNITO);
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(chromeOptions));
                System.out.println("***** Browser is chrome *****");
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                //firefoxOptions.addArguments(INCOGNITO);
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver(firefoxOptions));
                System.out.println("***** Browser is firefox *****");
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                System.out.println("***** Browser is edge *****");
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                System.out.println("***** Browser is chrome-bonigarcia *****");
                break;
        }

        driver.get().manage().window().maximize();
        return driver.get();
    }

    @Step("Create Remote Driver")
    private static RemoteWebDriver createRemoteDriver(String browser) throws MalformedURLException {

        if (browser.equals(CHROME)) {
            driver.set(createRemoteChromeDriver());
        } else if (browser.equals(FIREFOX)) {
            driver.set(createRemoteFirefoxDriver());
        }
        driver.get().manage().window().maximize();
        return driver.get();
    }

    private static RemoteWebDriver createRemoteChromeDriver() throws MalformedURLException {

        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(INCOGNITO);

        return new RemoteWebDriver(new URL(REMOTE_URL), chromeOptions);
    }

    private static RemoteWebDriver createRemoteFirefoxDriver() throws MalformedURLException {

        var fireFoxOptions = new FirefoxOptions();
        fireFoxOptions.addArguments(INCOGNITO);

        return new RemoteWebDriver(new URL(REMOTE_URL), fireFoxOptions);
    }

    @Step("Quit driver")
    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();
            driver.remove();
        }
    }
}
