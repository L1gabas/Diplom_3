package site.nomoreparties.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.models.UserModel;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;
    private final By profilePageConstructorButton = By.xpath(".//*[text()='Конструктор']");
    private final By exitButton = By.xpath(".//*[text()='Выход']");
    private final By stellarLogo = By.xpath(".//div/a");
    private final By profileEmail = By.xpath(".//*[text()='Логин']/parent::div/input[@name='name']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickOnConstructorButtonOnProfilePage(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(profilePageConstructorButton));
        driver.findElement(profilePageConstructorButton).click();
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickOnExitButton(){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }

    @Step("Клик по логотипу Стеллар-Бургер")
    public void clickOnStellarLogo(){
        driver.findElement(stellarLogo).click();
    }

    @Step("Проверка что пользователь авторизован")
    public boolean userCorrectlyAuth(UserModel user){
        Boolean correctEmail = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.textToBePresentInElementValue(profileEmail, user.getEmail()));
        return correctEmail;
    }
}
