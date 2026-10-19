package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalCabinetAuthorizationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PersonalCabinetAuthorizationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Локаторы - придуманные заглушки, т.к. сайт не работает

    // Локатор для поля Имя в личном кабинете
    private final By nameInput = By.xpath("//div[contains(text(), 'Имя')]//following::input");

    // Локатор для поля Логин в личном кабинете
    private final By loginInput = By.xpath("//div[contains(text(), 'Логин')]//following::input");

    // Метод проверки заполнения поля Имя
    public String getUserName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).getAttribute("value");
    }

    // Метод проверки заполнения поля Логин
    public String getUserLogin() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput)).getAttribute("value");
    }

    // Метод проверки соответствия имени тестовым данным
    public boolean isNameCorrect(String expectedName) {
        String actualName = getUserName();
        return actualName.equals(expectedName);
    }

    // Метод проверки соответствия логина тестовым данным
    public boolean isLoginCorrect(String expectedLogin) {
        String actualLogin = getUserLogin();
        return actualLogin.equals(expectedLogin);
    }

    // Метод для проверки обоих полей сразу
    public boolean verifyUserDetails(String expectedName, String expectedLogin) {
        return isNameCorrect(expectedName) && isLoginCorrect(expectedLogin);
    }
}
