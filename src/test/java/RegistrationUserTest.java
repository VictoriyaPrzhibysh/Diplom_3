import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserLoginRequestModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import pages.RegistrationPage;
import steps.UserSteps;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private RegistrationPage page;
    private UserData userData;
    private String accessToken;

    @Before
    public void setUp() {

        userData = new UserData();

        String browser = System.getProperty("browser", "chrome").toLowerCase();
        ChromeOptions options = new ChromeOptions();

        if ("yandex".equals(browser)) {
            System.out.println("Запуск тестов в Яндекс Браузере.");
            String yandexBinaryPath = "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";
            options.setBinary(yandexBinaryPath);

        } else {
            System.out.println("Запуск тестов в Google Chrome.");
        }

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get(MainPage.BASE_URL);
        page = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Проверяем, что пользоватеть может зарегистрироваться")
    public void testRegistrationUser() {
        String name = userData.FIRSTNAME;
        String email = userData.getRandomEmail();
        String password = userData.getRandomPassword();
        // Регистрируемся через браузер
        page.fillRegistrationForm(name, email, password);
        // Проверяем что регистрация прошла успешно
        assertTrue(
                "Не удалось зарегистрировать пользователя: сообщение об успехе не появилось",
                page.isRegistrationSuccess()
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
        // Регистрируемся через браузер
        page.fillRegistrationForm(name, email, password);
        // Проверяем что регистрация не прошла
        assertTrue(
                "Удалось зарегистрировать пользователя: сообщение об ошибке не появилось",
                page.isPasswordErrorDisplayed()
        );
    }

    @After
    public void tearDown() {
        // Удаляем пользователя,
        if (accessToken != null && !accessToken.isEmpty()) {
            Response deleteResponse = UserSteps.deleteUser(accessToken);
            deleteResponse.then().statusCode(200);
        }
        // Закрываем браузер
        if (driver != null) {
            driver.quit();
        }
    }
}