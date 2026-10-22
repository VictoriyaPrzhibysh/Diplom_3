package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Локатор для поля Имя
    private final By nameInput = By.xpath("//label[normalize-space()='Имя']/following::input[1]");

    // Локатор для поля Email
    private final By emailInput = By.xpath("//label[normalize-space()='Email']/following::input[1]");

    // Локатор для поля Пароль
    private final By passwordInput = By.xpath("//label[normalize-space()='Пароль']/following::input[1]");

    // Локатор для кнопки Зарегистрироваться на странице регистрации
    private final By registerButton = By.xpath("//button[normalize-space()='Зарегистрироваться']");

    // Локатор для кнопки Войти на странице регистрации
    private final By loginButton = By.xpath("//a[normalize-space()='Войти']");

    // Локатор для ошибки ввода неверного пароля (ПРЕДПОЛОЖЕНИЕ)
    private final By passwordError = By.cssSelector("p.input__error");

    @Step("Заполняем поле Имя")
    public void setName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameInput)).sendKeys(name);
    }

    @Step("Заполняем поле Email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(email);
    }
    @Step("Заполняем поле Пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    @Step("Нажимаем на кнопку Зарегистрироваться на странице регистрации")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Нажимаем на кнопку Войти на странице регистрации")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Заполняем форму регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверяем наличие ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(passwordError));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
