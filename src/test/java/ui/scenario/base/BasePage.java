package ui.scenario.base;

import ui.factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected static final Duration DEFAULT_WAIT = Duration.ofSeconds(30);

    /**
     * Use this method to find elements by By
     *
     * @return A list of WebElements, or an empty if nothing matches
     */
    protected List<WebElement> findElements(By by) {

        return DriverFactory.getDriver().findElements(by);
    }

    /**
     * Use this method to find element by By
     *
     * @return WebElement
     */
    protected WebElement findElement(By by) {

        return DriverFactory.getDriver().findElement(by);
    }

    /**
     * Use this method click to element
     */
    protected void clickElement(By by) {

        waitUntilElementAppears(by);
        waitUntilClickableAndGetElement(by).click();
    }

    /**
     * Wait until element to be clickable
     */
    protected WebElement waitUntilClickableAndGetElement(By by) {

        return new WebDriverWait(DriverFactory.getDriver(), DEFAULT_WAIT)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Wait until element appears
     */
    protected void waitUntilElementAppears(By by) {

        new WebDriverWait(DriverFactory.getDriver(), DEFAULT_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Wait until element disappears
     *
     * @param by
     */
    protected void waitUntilElementDisappears(By by) {

        new WebDriverWait(DriverFactory.getDriver(), DEFAULT_WAIT).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * @return True if element exists, false otherwise.
     */
    protected boolean isElementExist(By by, boolean... checkExistence) {

        if (checkExistence.length == 0 || checkExistence[0]) {

            try {

                waitUntilElementAppears(by);
                return findElements(by).size() == 1;
            } catch (TimeoutException e) {

                return false;
            }
        }

        try {

            waitUntilElementDisappears(by);
            return true;
        } catch (TimeoutException e) {

            return false;
        }
    }

    /**
     * Hover on an element
     */
    protected void hoverElement(By by) {

        WebElement element = waitUntilAppearsAndGetElement(by);
        new Actions(DriverFactory.getDriver()).moveToElement(element).perform();
    }

    /**
     * Wait until element appears
     */
    protected WebElement waitUntilAppearsAndGetElement(By by) {

        WebElement element = new WebDriverWait(DriverFactory.getDriver(), DEFAULT_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }

    /**
     * Get the visible (i.e. not hidden by CSS) innerText of this element.
     *
     * @return The innerText of this element.
     */
    protected String getTextOfElement(By by) {

        return waitUntilAppearsAndGetElement(by).getText();
    }

    /**
     * Use this method to simulate typing into an element if it is enable.
     * Send enter if pressEnter is true, do nothing otherwise.
     * Note : Before sending operation, element is cleared.
     */
    protected void fillInputField(By by, String text, boolean pressEnter) {

        WebElement element = waitUntilClickableAndGetElement(by);

        clearElementField(by);
        element.sendKeys(text);

        if (pressEnter) element.sendKeys(Keys.ENTER);
    }

    /**
     * Use this method to clear element
     */
    protected void clearElementField(By by) {

        WebElement element = waitUntilAppearsAndGetElement(by);
        element.click();

        element.clear();
    }

    /**
     * @return The attribute value of this element.
     */
    protected String getAttributeOfElement(By by, String attributeName) {

        return waitUntilAppearsAndGetElement(by).getAttribute(attributeName);
    }

    /**
     * Switch tab to given index
     */
    protected void switchTab(int tabIndex) {

        ArrayList<String> tabs = new ArrayList<>(DriverFactory.getDriver().getWindowHandles());
        DriverFactory.getDriver().switchTo().window(tabs.get(tabIndex));
    }

    /**
     * Scroll to the element with Javascript Executor
     */
    protected void executeScrollScript(By by) {

        DriverFactory.getDriver().executeScript("arguments[0].scrollIntoView(true);", waitUntilAppearsAndGetElement(by));
    }
}
