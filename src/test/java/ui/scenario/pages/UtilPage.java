package ui.scenario.pages;

import ui.factory.DriverFactory;
import io.qameta.allure.Step;
import ui.scenario.base.BasePage;

public class UtilPage extends BasePage {

    @Step("Navigate to URL")
    public void navigateUrl(String url) {

        DriverFactory.getDriver().get(url);
    }

    @Step("Get Page URL")
    public String getPageUrl() {

        return DriverFactory.getDriver().getCurrentUrl();
    }

    @Step("Switch to tab")
    public UtilPage switchToTab(int tabIndex) {

        switchTab(tabIndex);
        return this;
    }

    @Step("Close the tab")
    public UtilPage closeTab() {

        DriverFactory.getDriver().close();
        switchTab(0);

        return this;
    }
}
