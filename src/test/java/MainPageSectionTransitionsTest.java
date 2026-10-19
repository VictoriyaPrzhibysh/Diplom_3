import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.RegistrationPage;
import steps.UserSteps;

import static org.junit.Assert.assertTrue;

public class MainPageSectionTransitionsTest {

    private WebDriver driver;

    private MainPage mainPage;

    @Before
    public void setUp() {


        driver = DriverManager.getDriver();

        driver.get(MainPage.BASE_URL);

        mainPage = new MainPage(driver);

    }

    @Test
    @DisplayName("Переход к разделу Булочки")
    @Description("Проверяем, что работает переход к разделу Булочки")

    public void testMainPageSectionTransitionsBuns () {
        mainPage.clickBunsTab();
        assertTrue("Вкладка 'Булочки' не стала активной", mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description("Проверяем, что работает переход к разделу Соусы")

    public void testMainPageSectionTransitionsSauces () {
        mainPage.clickSaucesTab();
        assertTrue("Вкладка 'Соусы' не стала активной", mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("Проверяем, что работает переход к разделу Начинки")

    public void testMainPageSectionTransitionsFillings () {
        mainPage.clickFillingsTab();
        assertTrue("Вкладка 'Начинки' не стала активной", mainPage.isFillingsTabActive());
    }

    @After
    public void tearDown() {
        // Закрываем браузер
        DriverManager.quitDriver();
    }
}
