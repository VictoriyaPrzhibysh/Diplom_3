package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {

    private WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы - придуманные заглушки, т.к. сайт не работает

    // Локатор для поля Email
    private final By emailInput = By.cssSelector("input[placeholder*='Email']");

    // Локатор для поля Пароль
    private final By passwordInput = By.cssSelector("input[placeholder*='Пароль']");

    // Локатор для кнопки Войти на странице входа
    private final By authorizationButton = By.xpath("//div[contains(@class, 'authorizationButton')]//button[normalize-space()='Войти']");

    // Локатор для кнопки Зарегистрироваться на странице Личный кабинет (окно авторизации)
    private final By registerButton = By.xpath("//div[contains(@class, 'registerButton')]//button[normalize-space()='Зарегистрироваться']")

    // Локатор для кнопки Восстановить пароль на странице Личный кабинет (окно авторизации)
    private final By forgotPasswordButton = By.xpath("//a[normalize-space()='Восстановить пароль']");


    // Метод для заполнения поля Email
    public void setEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(emailInput))
                .sendKeys(email);
    }

    // Метод для заполнения поля Пароль
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Метод для нажатия на кнопку Войти
    public void clickAuthorizationButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(authorizationButton))
                .click();
    }

    // Метод для нажатия на кнопку Зарегистрироваться на странице Личный кабинет (окно авторизации)
    public void clickRegisterOnMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    // Метод для нажатия на кнопку Восстановить пароль
    public void clickForgotPasswordButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(forgotPasswordButton))
                .click();
    }

}
