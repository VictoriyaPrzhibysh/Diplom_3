package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinetAuthorizationPage {
    private WebDriver driver;

    public PersonalCabinetAuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы - придуманные заглушки, т.к. сайт не работает

    // Локатор для поля Имя в личном кабинете
    private final By nameInput = By.xpath("//div[contains(text(), 'Имя')]//following::input");

    // Локатор для поля Логин в личном кабинете
    private final By loginInput = By.xpath("//div[contains(text(), 'Логин')]//following::input");
}
