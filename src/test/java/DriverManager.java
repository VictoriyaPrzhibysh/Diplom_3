    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;

    public class DriverManager {

        private static WebDriver driver;

        public static WebDriver getDriver() {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            ChromeOptions options = new ChromeOptions();

            if ("yandex".equals(browser)) {
                System.out.println("Запускаем Яндекс Браузера");
                options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
            } else {
                System.out.println("Запускаем Chrome");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            return driver;
        }

        public static void quitDriver() {
            if (driver != null) {
                System.out.println("Закрываем браузер.");
                driver.quit();
                driver = null;
            }
        }
    }