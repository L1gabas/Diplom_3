package site.nomoreparties.stellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    private final By headerLogInButton = By.xpath(".//*[text()='Личный Кабинет']");
    private final By mainPageLogInButton = By.xpath(".//*[text()='Войти в аккаунт']");
    private final By mainPageBuildOrderButton = By.xpath(".//*[text()='Оформить заказ']");
    private final By bunButtonTab = By.xpath(".//*[contains(@class, \"tab_tab__1SPyG\")][1]");
    private final By sauceButtonTab = By.xpath(".//*[contains(@class, \"tab_tab__1SPyG\")][2]");
    private final By fillingButtonTab = By.xpath(".//*[contains(@class, \"tab_tab__1SPyG\")][3]");
    private final String currentTabCheck = ("tab_tab_type_current__2BEPc");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickOnMainPageHeaderLogInButton(){
        new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(ExpectedConditions.elementToBeClickable(headerLogInButton));
        driver.findElement(headerLogInButton).click();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickOnMainPageLogInButton(){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageLogInButton));
        driver.findElement(mainPageLogInButton).click();
    }

    @Step("Проверка отображения кнопки 'Оформить заказ' при успешном входе")
    public boolean visibilityBuildOrderButton(){
        WebElement buildOrderButton = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageBuildOrderButton));
        return buildOrderButton.isDisplayed();
    }

    @Step("Проверка перехода ко вкладке 'Булки' и нажатие на элемент вкладки")
    public boolean bunButtonConstructor(){
        driver.findElement(fillingButtonTab).click();
        driver.findElement(bunButtonTab).click();
        WebElement bun = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(bunButtonTab));
        return bun.getAttribute("class").contains(currentTabCheck);
    }

    @Step("Проверка перехода ко вкладке 'Соусы' и нажатие на элемент вкладки")
    public boolean sauceButtonConstructor(){
        driver.findElement(sauceButtonTab).click();
        WebElement sauce = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceButtonTab));
        return sauce.getAttribute("class").contains(currentTabCheck);
    }

    @Step("Проверка перехода ко вкладке 'Начинки' и нажатие на элемент вкладки")
    public boolean fillingButtonConstructor(){
        driver.findElement(fillingButtonTab).click();
        WebElement filling = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingButtonTab));
        return filling.getAttribute("class").contains(currentTabCheck);
    }
}