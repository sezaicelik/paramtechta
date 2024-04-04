package ui.scenario.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.scenario.base.BasePage;

public class HomePage extends BasePage {
    private static final By GIRIS_YAP_BUTTON = By.id("menu_login");

    @Step("Click Giris Yap button")
    public CreateAccountPage clickGirisYapButton() {

        clickElement(GIRIS_YAP_BUTTON);
        return new CreateAccountPage();
    }
}
