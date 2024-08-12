package site.nomoreparties.stellarburgers.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.models.UserModel;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SignUpPage {

    private final WebDriver driver;
    private final By inputStringSingUpPageEmail = By.xpath(".//*[text()='Email']/parent::div/input[@name='name']");
    private final By inputStringSingUpPageName = By.xpath(".//*[text()='Имя']/parent::div/input[@name='name']");
    private final By inputStringSingUpPagePassword = By.xpath(".//*[text()='Пароль']//following-sibling::input");
    private final By signUpPageEnterButton = By.xpath(".//*[text()='Войти']");
    private final By signUpRegistrationButton = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By incorrectPassErrorSignUpPage = By.xpath(".//*[text()='Некорректный пароль']");

    public SignUpPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickOnRegistrationInSignUpPageButton(){
        driver.findElement(signUpRegistrationButton).click();
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickOnEnterButtonInSignUpPage(){
        driver.findElement(signUpPageEnterButton).click();
    }

    @Step("Заполнение данных пользователя")
    public void fillUsersData(UserModel user){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(inputStringSingUpPagePassword));
        driver.findElement(inputStringSingUpPageName).sendKeys(user.getName());
        driver.findElement(inputStringSingUpPageEmail).sendKeys(user.getEmail());
        driver.findElement(inputStringSingUpPagePassword).sendKeys(user.getPassword());
    }

    @Step("Ввод некорректного пароля")
    public void incorrectPassInput(UserModel user){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(inputStringSingUpPagePassword));
        driver.findElement(inputStringSingUpPagePassword).sendKeys(user.getPassword());
    }

    @Step("Проверка отображения сообщения при вводе невалидного пароля")
    public boolean incorrectPassAndMessageCheck(){
        WebElement incorrectPassMessage = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPassErrorSignUpPage));
        return incorrectPassMessage.isDisplayed();
    }
}
