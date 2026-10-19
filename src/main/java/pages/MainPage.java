package pages;

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
        wait.until(ExpectedConditions.elementToBeClickable(personalCabinetButton)).click();
    }

    // Метод для нажатия на кнопку Войти в аккаунт
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    // Метод нажатия на вкладку Булки
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    // Метод нажатия на вкладку Соусы
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }


    //Метод нажатия на вкладку Начинки
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    //Метод проверки активности вкладки Булки
    public boolean isBunsTabActive() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        String classes = element.getAttribute("class");
        return classes != null && classes.contains("tab_tab_type_current");
    }

    //Метод проверки активности вкладки Соусы
    public boolean isSaucesTabActive() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab));
        String classes = element.getAttribute("class");
        return classes != null && classes.contains("tab_tab_type_current");
    }

    //Метод проверки активности вкладки Начинки
    public boolean isFillingsTabActive() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
        String classes = element.getAttribute("class");
        return classes != null && classes.contains("tab_tab_type_current");
    }
}
