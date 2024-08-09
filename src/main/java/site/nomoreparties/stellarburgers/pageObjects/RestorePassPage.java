package site.nomoreparties.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePassPage {

    private final WebDriver driver;

    private final By restoreEnterButton = By.xpath(".//*[text()='Войти']");
    public RestorePassPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void restoreEnterButtonClick(){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(restoreEnterButton));
        driver.findElement(restoreEnterButton).click();
    }
}
