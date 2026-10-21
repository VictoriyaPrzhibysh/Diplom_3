import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import steps.UserSteps;

import static org.junit.Assert.assertTrue;

public class AuthorizationUserTest extends BaseApiTest  {

    private WebDriver driver;
    private UserData userData;

    private String name;
    private String email;
    private String password;
    private String accessToken;

    private MainPage mainPage;
    private AuthorizationPage authorizationPage;
    private PersonalCabinetAuthorizationPage personalCabinetAuthorizationPage;
    private RegistrationPage registrationPage;
    private PasswordRecoveryPage passwordRecoveryPage;

    @Before
    public void setUp() {

        userData = new UserData();

        // Создаем данные пользователя
        name = userData.FIRSTNAME;
        email = userData.getRandomEmail();
        password = userData.getRandomPassword();

        // Создаем пользователя
        UserModel user = new UserModel(email, password, name);

        Response createResponse = UserSteps.createUser(user);
        createResponse.then().statusCode(200);
        System.out.println("Пользователь создан: " + email);

        driver = DriverManager.getDriver();
        driver.get(MainPage.BASE_URL);

        mainPage = new MainPage(driver);
        authorizationPage = new AuthorizationPage(driver);
        personalCabinetAuthorizationPage = new PersonalCabinetAuthorizationPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
     }

    @Test
    @DisplayName("Авторизация пользователя по кнопке «Войти в аккаунт» на главной")
    @Description("Проверяем, что пользоватеть может войти по кнопке «Войти в аккаунт» на главной")
    public void testLoginViaMainPageButton() {
        mainPage.clickLoginLink();
        authorizationPage.fillAuthorizationForm (email, password);
        mainPage.clickPersonalCabinetButton();
        personalCabinetAuthorizationPage.verifyUserDetails(name, email);
    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке Личный кабинет")
    @Description("Проверяем, что пользоватеть может войти по кнопке Личный кабинет")
    public void testLoginViaPersonalCabinetButton() {
        mainPage.clickPersonalCabinetButton();
        authorizationPage.fillAuthorizationForm (email, password);
        mainPage.clickPersonalCabinetButton();
        personalCabinetAuthorizationPage.verifyUserDetails(name, email);
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку Войти в форме регистрации")
    @Description("Проверяем, что пользоватеть может войти по кнопке Войти в форме регистрации")
    public void testLoginViaRegistrationButton() {
        mainPage.clickLoginLink();
        authorizationPage.clickRegisterButton();
        registrationPage.clickLoginButton();
        authorizationPage.fillAuthorizationForm (email, password);
        mainPage.clickPersonalCabinetButton();
        personalCabinetAuthorizationPage.verifyUserDetails(name, email);
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку Войти в форме восстановления пароля")
    @Description("Проверяем, что пользоватеть может войти по кнопке Войти в форме восстановления пароля")
    public void testLoginViaPasswordRecoveryPage() {
        mainPage.clickLoginLink();
        authorizationPage.clickForgotPasswordButton();
        passwordRecoveryPage.clickLoginButton();
        authorizationPage.fillAuthorizationForm (email, password);
        mainPage.clickPersonalCabinetButton();
        personalCabinetAuthorizationPage.verifyUserDetails(name, email);
    }


    @After
    public void tearDown() {
        // Получаем токен для удаления пользователя
        accessToken = UserSteps.getAccessToken(email, password);
        assertTrue("Не удалось получить токен для очистки", accessToken != null);
        // Удаляем пользователя,
        if (accessToken != null && !accessToken.isEmpty()) {
            Response deleteResponse = UserSteps.deleteUser(accessToken);
            deleteResponse.then().statusCode(202);
        }
        // Закрываем браузер
        DriverManager.quitDriver();
    }
}
