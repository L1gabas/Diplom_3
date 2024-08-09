package site.nomoreparties.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.models.UserModel;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final By registrationButton = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By inputStringEmail = By.xpath(".//*[text()='Email']/parent::div/input[@name='name']");
    private final By inputStringPassword = By.xpath(".//*[text()='Пароль']//following-sibling::input");
    private final By enterButton = By.xpath(".//*[text()='Войти']");
    private final By restorePasswordButton = By.xpath(".//*[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickOnRegistrationButton(){
        driver.findElement(registrationButton).click();
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void clickOnRecoverPasswordButton(){
        driver.findElement(restorePasswordButton).click();
    }

    @Step("Заполнение данных для входа в Личный кабинет")
    public void fillUsersDataToLogIn(UserModel user){
        new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(ExpectedConditions.elementToBeClickable(inputStringPassword));
        driver.findElement(inputStringEmail).sendKeys(user.getEmail());
        driver.findElement(inputStringPassword).sendKeys(user.getPassword());
        driver.findElement(enterButton).click();
    }

    @Step("Проверка что пользователь корректно вышел")
    public boolean userExitCheck(){
        Boolean emptyEmailField = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.textToBe(inputStringEmail, ""));
        return emptyEmailField;
    }


    @Step("Обновление страницы")
    public void refreshLoginPage(){
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }
}
