package pages;

import io.qameta.allure.Step;
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

    // Локатор для поля Имя в личном кабинете
    private final By nameInput = By.name("Name");

    // Локатор для поля Логин в личном кабинете
    private final By loginInput = By.name("name");

    @Step("Проверяем заполнение поля Имя")
    public String getUserName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).getAttribute("value");
    }

    @Step("Проверяем заполнение поля Логин")
    public String getUserLogin() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput)).getAttribute("value");
    }

    @Step("Проверяем соответствия имени данным залогиневшегося пользователя")
    public boolean isNameCorrect(String expectedName) {
        String actualName = getUserName();
        return actualName.equals(expectedName);
    }

    @Step("Проверяем соответствия логина данным залогиневшегося пользователя")
    public boolean isLoginCorrect(String expectedLogin) {
        String actualLogin = getUserLogin();
        return actualLogin.equals(expectedLogin);
    }

    @Step("Проверяем соответствия имени и логина данным залогиневшегося пользователя")
    public boolean verifyUserDetails(String expectedName, String expectedLogin) {
        return isNameCorrect(expectedName) && isLoginCorrect(expectedLogin);
    }
}
