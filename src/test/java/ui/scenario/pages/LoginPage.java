package ui.scenario.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.scenario.base.BasePage;

public class LoginPage extends BasePage {
    private static final By CARD_NO_INPUT = By.cssSelector("[name='userCardNo']");
    private static final By PASSWORD_INPUT = By.cssSelector("[name='userPassword']");
    private static final By GIRIS_YAP_BUTTON = By.id("loginbutton");
    private static final By POPUP_TAMAM_BUTTON = By.xpath("//*[contains(@class,'modal-content')]/..//*[contains(@class,'buttonPrimary')]");
    private static final By POPUP_BODY = By.xpath("//*[contains(@class,'modal-content')]/..//*[contains(@class,'modalTitle')]");
    private static final By HESAP_OLUSTUR_BUTTON = By.xpath("//*[contains(@class, 'signUp')]");

    @Step("Type Card No")
    public LoginPage typeCardNo(String username) {

        fillInputField(CARD_NO_INPUT, username, false);
        return this;
    }

    @Step("Type Password")
    public LoginPage typePassword(String password) {

        fillInputField(PASSWORD_INPUT, password, false);
        return this;
    }

    @Step("Click Giris Yap button")
    public LoginPage clickGirisYapButton() {

        clickElement(GIRIS_YAP_BUTTON);
        return this;
    }

    @Step("Get Popup body message")
    public String getPopupBodyMessage() {

        var popupBodyText = getTextOfElement(POPUP_BODY);

        if (popupBodyText.contains("\n")) {
            popupBodyText = popupBodyText.replaceAll("\n", "");
        }

        return popupBodyText;
    }

    @Step("Click Popup Tamam button")
    public LoginPage clickPopupTamamButton() {

        clickElement(POPUP_TAMAM_BUTTON);
        return this;
    }

    @Step("Click Hesap Olustur button")
    public SignUpPage clickHesapOlusturButton() {

        clickElement(HESAP_OLUSTUR_BUTTON);
        return new SignUpPage();
    }
}
