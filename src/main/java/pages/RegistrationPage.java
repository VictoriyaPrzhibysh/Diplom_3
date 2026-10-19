package pages;

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

    // Локаторы - придуманные заглушки, т.к. сайт не работает

    // Локатор для поля Имя
    private final By nameInput = By.cssSelector("input[placeholder*='Имя']");

    // Локатор для поля Email
    private final By emailInput = By.cssSelector("input[placeholder*='Email']");

    // Локатор для поля Пароль
    private final By passwordInput = By.cssSelector("input[placeholder*='Пароль']");

    // Локатор для кнопки Зарегистрироваться на странице регистрации
    private final By registerButton = By.xpath("//div[contains(@class, 'registerButton')]//button[normalize-space()='Зарегистрироваться']");

    // Локатор для кнопки Войти на странице регистрации
    private final By loginButton = By.xpath("//div[contains(@class, 'registerButton')]//button[normalize-space()='Войти']");

    // Локатор для ошибки ввода неверного пароля (ПРЕДПОЛОЖЕНИЕ)
    private final By passwordError = By.cssSelector(".error-message");

    // Локатор для окна подтверждения успешной регистрации (ПРЕДПОЛОЖЕНИЕ)
    private final By registrationSuccessMessage = By.cssSelector(".success-message");


    // Метод для заполнения поля Имя
    public void setName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameInput)).sendKeys(name);
    }

    // Метод для заполнения поля Email
    public void setEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(email);
    }

    // Метод для заполнения поля Пароль
    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    // Метод нажатия на кнопку Зарегистрироваться на странице регистрации
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    // Метод нажатия на кнопку Войти на странице регистрации
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // Метод для заполнения формы регистрации
    public void fillRegistrationForm(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    // Метод проверки наличия ошибки пароля (ПРЕДПОЛОЖЕНИЕ)
    public boolean isPasswordErrorDisplayed() {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(passwordError));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Метод проверки успешного завершения регистрации (ПРЕДПОЛОЖЕНИЕ)
    public boolean isRegistrationSuccess() {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(registrationSuccessMessage));
            return element.isDisplayed() && element.getText().contains("успешно");
        } catch (Exception e) {
            return false;
        }
    }

}
