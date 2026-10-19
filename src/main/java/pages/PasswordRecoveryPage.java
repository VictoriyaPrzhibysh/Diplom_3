package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage {

    private WebDriver driver;
    private final WebDriverWait wait;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Локаторы - придуманные заглушки, т.к. сайт не работает

    // Локатор для кнопки Войти
    private final By loginButton = By.xpath("//button[normalize-space()='Войти']");

    // Метод для нажатия на кнопку Войти
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}
