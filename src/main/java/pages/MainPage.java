package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    // Локаторы взяты с записи вебинара
    // Локатор для вкладки Бургер
    private final By bunsTab = By.xpath("//span[contains(text(), 'Булки')]");

    // Локатор для вкладки Соусы
    private final By saucesTab = By.xpath("//span[contains(text(), 'Соусы')]");

    // Локатор для вкладки Начинки
    private final By fillingsTab = By.xpath("//span[contains(text(), 'Начинки')]");

    // Локаторы - придуманные заглушки, т.к. сайт не работает
    // Локатор для кнопки Личный кабинет
    private final By personalCabinetButton = By.xpath("//button[normalize-space()='Личный кабинет']");

    // Локатор для кнопки Войти в аккаунт на главной странице
    private final By loginLink = By.xpath("//button[contains(., 'Войти в аккаунт')]");

    // Метод для нажатиям на кнопку Личный кабинет
    public void clickPersonalCabinetButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(personalCabinetButton))
                .click();
    }

    // Метод для нажатиям на кнопку Войти в аккаунт
    public void clickLoginLink() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    // Метод нажатия на вкладку Булки
    public void clickBunsTab() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(bunsTab))
                .click();
    }

    // Метод нажатия на вкладку Соусы
    public void clickSaucesTab() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(saucesTab))
                .click();
    }

    //Метод нажатия на вкладку Начинки
    public void clickFillingsTab() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(fillingsTab))
                .click();
    }

    //Метод проверки активности вкладки Булки
    public boolean isBunsTabActive() {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
            return element.getAttribute("class").contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
    }

    //Метод проверки активности вкладки Соусы
    public boolean isSaucesTabActive() {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(saucesTab));
            return element.getAttribute("class").contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
    }

    //Метод проверки активности вкладки Начинки
    public boolean isFillingsTabActive() {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
            return element.getAttribute("class").contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
}
