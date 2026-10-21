package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    // Локатор для вкладки Бургер
    private final By bunsTab = By.xpath("//div[contains(@class, 'tab_tab') and .//span[contains(text(), 'Булки')]]");

    // Локатор для вкладки Соусы
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab_tab') and .//span[contains(text(), 'Соусы')]]");

    // Локатор для вкладки Начинки
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab') and .//span[contains(text(), 'Начинки')]]");

    // Локатор для кнопки Личный кабинет
    private final By personalCabinetButton = By.linkText("Личный Кабинет");

    // Локатор для кнопки Войти в аккаунт на главной странице
    private final By loginLink = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx");

    @Step("Нажимаем на кнопку Личный кабинет")
    public void clickPersonalCabinetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalCabinetButton)).click();
    }

    @Step("Нажимаем на кнопку Войти в аккаунт")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Нажимаем на вкладку Булки")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Нажимаем на вкладку Соусы")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Нажимаем на вкладку Начинки")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Проверяем активность вкладки Булки")
    public boolean isBunsTabActive() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        String classes = element.getAttribute("class");
        return classes != null && classes.contains("tab_tab_type_current");
    }

    @Step("Проверяем активность вкладки Соусы")
    public boolean isSaucesTabActive() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab));
        String classes = element.getAttribute("class");
        return classes != null && classes.contains("tab_tab_type_current");
    }

    @Step("Проверяем активность вкладки Начинки")
    public boolean isFillingsTabActive() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
        String classes = element.getAttribute("class");
        return classes != null && classes.contains("tab_tab_type_current");
    }
}
