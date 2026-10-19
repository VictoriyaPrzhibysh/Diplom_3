package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {

    private WebDriver driver;
    private final WebDriverWait wait;

    public AuthorizationPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Локаторы - придуманные заглушки, т.к. сайт не работает

    // Локатор для поля Email
    private final By emailInput = By.cssSelector("input[placeholder*='Email']");

    // Локатор для поля Пароль
    private final By passwordInput = By.cssSelector("input[placeholder*='Пароль']");

    // Локатор для кнопки Войти на странице входа
    private final By authorizationButton = By.xpath("//div[contains(@class, 'authorizationButton')]//button[normalize-space()='Войти']");

    // Локатор для кнопки Зарегистрироваться на странице Личный кабинет (окно авторизации)
    private final By registerButton = By.xpath("//div[contains(@class, 'registerButton')]//button[normalize-space()='Зарегистрироваться']");

    // Локатор для кнопки Восстановить пароль на странице Личный кабинет (окно авторизации)
    private final By forgotPasswordButton = By.xpath("//a[normalize-space()='Восстановить пароль']");


    // Метод для заполнения поля Email
    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(email);
    }

    // Метод для заполнения поля Пароль
    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    // Метод для нажатия на кнопку Войти
    public void clickAuthorizationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(authorizationButton)).click();
    }

    // Метод для нажатия на кнопку Зарегистрироваться на странице Личный кабинет (окно авторизации)
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    // Метод для нажатия на кнопку Восстановить пароль
    public void clickForgotPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton)).click();
    }

    // Метод для заполнения формы авторизации
    public void fillAuthorizationForm (String email, String password) {
        setEmail(email);
        setPassword(password);
        clickAuthorizationButton();
    }
}
