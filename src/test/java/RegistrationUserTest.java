import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.RegistrationPage;
import steps.UserSteps;

import static org.junit.Assert.assertTrue;

public class RegistrationUserTest extends BaseApiTest {

    private WebDriver driver;
    private UserData userData;
    private String accessToken;

    private RegistrationPage registrationPage;
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;

    @Before
    public void setUp() {

        userData = new UserData();

        driver = DriverManager.getDriver();

        driver.get(MainPage.BASE_URL);

        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        authorizationPage = new AuthorizationPage(driver);

    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Проверяем, что пользоватеть может зарегистрироваться")
    public void testRegistrationUser()  {
        String name = userData.FIRSTNAME;
        String email = userData.getRandomEmail();
        String password = userData.getRandomPassword();

        mainPage.clickLoginLink();
        authorizationPage.clickRegisterButton();
        // Регистрируемся
        registrationPage.fillRegistrationForm(name, email, password);
        // Проверяем что регистрация прошла успешно, произошел переход на страницу авторизации
        assertTrue(
                "Не произошёл переход на страницу авторизации после регистрации. Текущий URL: \" + driver.getCurrentUrl()",
                authorizationPage.isOnAuthorizationPage()
        );
        // Получаем токен для очистки данных
        accessToken = UserSteps.getAccessToken(email, password);
        assertTrue("Не удалось получить токен", accessToken != null);
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    @Description("Проверяем, что пользоватеть не может зарегистрироваться с некорректным паролем")
    public void testBadRegistrationUser() {
        String name = userData.FIRSTNAME;
        String email = userData.getRandomEmail();
        String password = userData.getRandomBadPassword();

        mainPage.clickLoginLink();
        authorizationPage.clickRegisterButton();
        // Регистрируемся
        registrationPage.fillRegistrationForm(name, email, password);
        // Проверяем что регистрация не прошла
        assertTrue(
                "Удалось зарегистрировать пользователя: сообщение об ошибке не появилось",
                registrationPage.isPasswordErrorDisplayed()
        );
    }

    @After
    public void tearDown() {
        // Удаляем пользователя,
        if (accessToken != null && !accessToken.isEmpty()) {
            Response deleteResponse = UserSteps.deleteUser(accessToken);
            deleteResponse.then().statusCode(202);
        }
        // Закрываем браузер
        DriverManager.quitDriver();
    }
}