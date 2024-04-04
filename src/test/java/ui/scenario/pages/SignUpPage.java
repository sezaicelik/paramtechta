package ui.scenario.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.scenario.base.BasePage;

import java.util.LinkedList;

public class SignUpPage extends BasePage {

    private static final By NAME_INPUT = By.cssSelector("[name='name']");
    private static final By SURNAME_INPUT = By.cssSelector("[name='surname']");
    private static final By EMAIL_INPUT = By.cssSelector("[name='email']");
    private static final By GSM_NUMBER_INPUT = By.cssSelector("[name='gsmNumber']");
    private static final By AYDINLATMA_METNI_CHECKBOX = By.xpath("//*[@name='checkBox1']//*[contains(@src, '/static/')]");
    private static final By YURTDISI_METNI_CHECKBOX = By.xpath("//*[@name='checkBox2']//*[contains(@src, '/static/')]");
    private static final By ELEKTRONIK_METNI_CHECKBOX = By.xpath("//*[@name='checkBox3']//*[contains(@src, '/static/')]");
    private static final By ILETISIM_METNI_CHECKBOX = By.xpath("//*[@name='checkBox4']//*[contains(@src, '/static/')]");
    private static final By DEVAM_BUTTON = By.id("continue_from_signup");
    private static final By OTP_ONAYLA_BUTTON = By.id("continue_from_otp");
    private static final By PAGE_HEADER = By.xpath("//*[contains(@class, 'pageHeaderSmsVerification')]");
    private static final By OTP_INPUT_1 = By.name("input1");
    private static final By OTP_ERROR_MESSAGE_TEXT = By.id("errorVerificationNo");

    @Step("Type Name")
    public SignUpPage typeName(String name) {

        fillInputField(NAME_INPUT, name, false);
        return this;
    }

    @Step("Type Surname")
    public SignUpPage typeSurname(String surname) {

        fillInputField(SURNAME_INPUT, surname, false);
        return this;
    }

    @Step("Type Email")
    public SignUpPage typeEmail(String email) {

        fillInputField(EMAIL_INPUT, email, false);
        return this;
    }

    @Step("Type GSM Number")
    public SignUpPage typeGSMNumber(String gsmNumber) {

        fillInputField(GSM_NUMBER_INPUT, gsmNumber, false);
        return this;
    }

    @Step("Click Aydinlatma Metni checbox")
    public SignUpPage clickAydinlatmaMetniCheckbox() {

        clickElement(AYDINLATMA_METNI_CHECKBOX);
        return this;
    }

    @Step("Click Yurtdisi Metni checbox")
    public SignUpPage clickYurtdisiMetniCheckbox() {

        clickElement(YURTDISI_METNI_CHECKBOX);
        return this;
    }

    @Step("Click Elektronik Metni checbox")
    public SignUpPage clickElektronikMetniCheckbox() {

        clickElement(ELEKTRONIK_METNI_CHECKBOX);
        return this;
    }

    @Step("Click Iletisim Metni checbox")
    public SignUpPage clickIletisimMetniCheckbox() {

        clickElement(ILETISIM_METNI_CHECKBOX);
        return this;
    }

    @Step("Click Devam button")
    public SignUpPage clickDevamButton() {

        clickElement(DEVAM_BUTTON);
        return this;
    }

    @Step("Get Page header")
    public String getPageHeader() {

        return getTextOfElement(PAGE_HEADER);
    }

    @Step("Type OTP Password")
    public SignUpPage typeOTPPassword(int otpPassword) {

        LinkedList<Integer> result = new LinkedList<>();
        while (otpPassword > 0) {
            result.push(otpPassword % 10);
            otpPassword /= 10;
        }

        waitUntilAppearsAndGetElement(OTP_INPUT_1);

        for (var i = 0; i < result.size(); i++) {

            var xpath = "input" + (i + 1);
            fillInputField(By.name(xpath), String.valueOf(result.get(i)), false);
        }

        return this;
    }

    @Step("Click OTP Onayla Button")
    public SignUpPage otpOnaylaButton() {

        clickElement(OTP_ONAYLA_BUTTON);
        return this;
    }

    @Step("Get OTP Password Error message")
    public String getOTPErrorMessage() {

        return getTextOfElement(OTP_ERROR_MESSAGE_TEXT);
    }
}