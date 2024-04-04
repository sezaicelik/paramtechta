package ui.scenario.tests;

import ui.factory.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.scenario.base.BaseTest;
import ui.scenario.pages.HomePage;
import ui.scenario.pages.LoginPage;
import ui.scenario.pages.SignUpPage;
import ui.scenario.pages.UtilPage;

import java.net.MalformedURLException;
import java.time.ZonedDateTime;

public class ParamTechWebTests extends BaseTest {

    private HomePage homePage;
    private UtilPage utilPage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    public static final String PARAM_URL = "https://param.com.tr";

    @Parameters({"browserName", "executionType"})
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browser, @Optional("local") String executionType) throws MalformedURLException {

        homePage = new HomePage();
        utilPage = new UtilPage();
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();

        setUp(browser, executionType);
    }

    @BeforeMethod
    public void beforeMethod() {

        utilPage.navigateUrl(PARAM_URL);
    }

    @AfterClass
    public void afterClass() {

        DriverFactory.quitDriver();
    }

    //Test Case 1
    @Test(priority = 1, description = "Login test with invalid username and invalid password")
    public void invalidUserNameInvalidPasswordLoginTest() {

        final var CARD_NO = "1111222233334444";
        final var PASSWORD = "123456Abc";
        final var POPUP_ERROR_MESSAGE = "Hatalı bilgi girişi yaptınız. Lütfen bilgilerinizi kontrol ediniz.";

        homePage
                .clickGirisYapButton()
                .clickKurumsalGirisButton();

        utilPage
                .switchToTab(1);

        loginPage
                .typeCardNo(CARD_NO)
                .typePassword(PASSWORD)
                .clickGirisYapButton();

        var actualPopupMessage = loginPage.getPopupBodyMessage();

        Assert.assertEquals(actualPopupMessage, POPUP_ERROR_MESSAGE);

        loginPage
                .clickPopupTamamButton();

        utilPage.closeTab();
    }

    //Test Case 2
    @Test(priority = 2, description = "Create Account")
    public void createAccountTest() {

        final var NAME = "Sezai";
        final var SURNAME = "Celik";
        final var GSM_NUMBER = "5490000000";
        final var OTP_PASSWORD = 123456;
        final var SMS_ONAY_PAGE_HADER = "SMS Onay";
        final var OTP_ERROR_MESSAGE = "Girdiğiniz onay kodu hatalıdır.";

        var uniqueTime = ZonedDateTime.now().toInstant().toEpochMilli();
        var email = "sezaicelik" + "_" + uniqueTime + "@gmail.com";

        homePage
                .clickGirisYapButton()
                .clickKurumsalGirisButton();

        utilPage
                .switchToTab(1);

        loginPage
                .clickHesapOlusturButton()
                .typeName(NAME)
                .typeSurname(SURNAME)
                .typeEmail(email)
                .typeGSMNumber(GSM_NUMBER)
                .clickAydinlatmaMetniCheckbox()
                .clickYurtdisiMetniCheckbox()
                .clickElektronikMetniCheckbox()
                .clickIletisimMetniCheckbox()
                .clickDevamButton();

        var actualPageHeader = signUpPage.getPageHeader();

        Assert.assertEquals(actualPageHeader, SMS_ONAY_PAGE_HADER);

        signUpPage
                .typeOTPPassword(OTP_PASSWORD)
                .clickOTPOnaylaButton();

        var otpErrorMessage = signUpPage.getOTPErrorMessage();

        Assert.assertEquals(otpErrorMessage, OTP_ERROR_MESSAGE);
    }
}