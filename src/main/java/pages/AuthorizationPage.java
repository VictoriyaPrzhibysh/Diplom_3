package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {

    private WebDriver driver;
    private final WebDriverWait wait;
    private static final String AUTHORIZATION_PAGE_URL = "https://qa-stellarburgers.education-services.ru/login";

    public AuthorizationPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Локатор для поля Email
    private final By emailInput = By.xpath("//label[normalize-space()='Email']/following-sibling::input[1]");

    // Локатор для поля Пароль
    private final By passwordInput = By.name("Пароль");

    // Локатор для кнопки Войти на странице входа
    private final By authorizationButton = By.xpath("//button[normalize-space()='Войти']");

    // Локатор для кнопки Зарегистрироваться на странице Личный кабинет (окно авторизации)
    private final By registerButton = By.xpath("//a[normalize-space()='Зарегистрироваться']");

    // Локатор для кнопки Восстановить пароль на странице Личный кабинет (окно авторизации)
    private final By forgotPasswordButton = By.xpath("//a[normalize-space()='Восстановить пароль']");


    @Step("Заполнение поля Email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(email);
    }

    @Step("Заполнение поля Пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    @Step("Нажимаем на кнопку Войти")
    public void clickAuthorizationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(authorizationButton)).click();
    }

    @Step("Нажимаем на кнопку Зарегистрироваться на странице Личный кабинет (окно авторизации)")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Нажимаем на кнопку Восстановить пароль")
    public void clickForgotPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton)).click();
    }

    @Step("Проверяем что мы находимся на странице авторизации")
    public boolean isOnAuthorizationPage() {
        try {
            wait.until(ExpectedConditions.urlContains(AUTHORIZATION_PAGE_URL));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Заполненем форму авторизации")
    public void fillAuthorizationForm (String email, String password) {
        setEmail(email);
        setPassword(password);
        clickAuthorizationButton();
    }
}
