import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static site.nomoreparties.stellarburgers.commons.EpAndApi.TEST_STAND;

public class TransitionsTests extends BaseTest {

    @Before
    public void setUpsForTest(){
        steps.userData(user);
        steps.createUser(user);
        driver.get(TEST_STAND);
    }

    //Переходы личного кабинета

    @Test
    @DisplayName("Проверить переход по клику на 'Личный кабинет'")
    @Description("Проверка перехода по кнопке 'Личный кабинет'")
    public void transitionsFromMainPageLogInButton(){
        mainPage.clickOnMainPageLogInButton();
        loginPage.fillUsersDataToLogIn(user);
        mainPage.clickOnMainPageHeaderLogInButton();
        Assert.assertTrue("Email пользователя не совпадает", profilePage.userCorrectlyAuth(user));
    }

    @Test
    @DisplayName("Проверка перехода по клику на 'Конструктор'")
    @Description("Проверка перехода по кнопке 'Конструктор'")
    public void transitionsFromConstructorButton() {
        mainPage.clickOnMainPageLogInButton();
        loginPage.fillUsersDataToLogIn(user);
        mainPage.clickOnMainPageHeaderLogInButton();
        profilePage.clickOnConstructorButtonOnProfilePage();
        Assert.assertTrue("Кнопка 'Оформить заказ' отсутствует", mainPage.visibilityBuildOrderButton());
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип Stellar Burgers")
    @Description("Проверка перехода по нажатию на логотип Stellar Burgers")
    public void transitionsFromLogoSB() {
        mainPage.clickOnMainPageLogInButton();
        loginPage.fillUsersDataToLogIn(user);
        mainPage.clickOnMainPageHeaderLogInButton();
        profilePage.clickOnStellarLogo();
        Assert.assertTrue("Кнопка 'Оформить заказ' отсутствует", mainPage.visibilityBuildOrderButton());
    }

    //Переходы конструктора для создания бургера

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка, что работает переходы к разделу 'Булки'")
    public void transitionsToBunInConstructor(){
        Assert.assertTrue("Переход к вкладке 'Булки' не осуществлен", mainPage.bunButtonConstructor());

    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка, что работает переходы к разделу 'Соусы'")
    public void transitionsToSauceInConstructor(){
        Assert.assertTrue("Переход к вкладке 'Соусы' не осуществлен", mainPage.sauceButtonConstructor());

    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка, что работает переходы к разделу 'Начинки'")
    public void transitionsToFillingInConstructor(){
        Assert.assertTrue("Переход к вкладке 'Начинки' не осуществлен", mainPage.fillingButtonConstructor());

    }
}
