package ui.scenario.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.scenario.base.BasePage;

public class CreateAccountPage extends BasePage {

    private static final By KURUMSAL_GIRIS_BUTTON = By.xpath("//*[@class='bireysel-hesap-olustur-alt']//*[text()='Kurumsal Giriş']");

    @Step("Click Kurumsal Giris button")
    public CreateAccountPage clickKurumsalGirisButton() {

        clickElement(KURUMSAL_GIRIS_BUTTON);
        return this;
    }
}
