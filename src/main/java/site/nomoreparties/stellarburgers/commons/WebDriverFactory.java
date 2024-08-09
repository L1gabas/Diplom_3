package site.nomoreparties.stellarburgers.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver driver;

    public static WebDriver getWebDriver(){
        String browserName = System.getProperty("browser", "chrome");

        if(driver == null){
            switch (browserName){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();

                case "yandex":
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                    return new ChromeDriver();

                default:
                    throw new RuntimeException("Неверный браузер" + browserName);
            }
        }
        return driver;
    }
}
