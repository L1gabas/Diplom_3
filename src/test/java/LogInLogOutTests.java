import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageobjects.RestorePassPage;
import site.nomoreparties.stellarburgers.pageobjects.SignUpPage;

import static site.nomoreparties.stellarburgers.commons.EpAndApi.TEST_STAND;

public class LogInLogOutTests extends BaseTest {

    private SignUpPage signUpPage;
    private RestorePassPage restorePassPage;


    @Before
    public void setUpsForTest(){
        signUpPage = new SignUpPage(driver);
        restorePassPage = new RestorePassPage(driver);
        steps.userData(user);
        steps.createUser(user);
        driver.get(TEST_STAND);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Проверка входа с главной страницы портала по кнопке 'Войти в аккаунт'")
    public void logInFromMainPageLogInButton(){
        mainPage.clickOnMainPageLogInButton();
        loginPage.fillUsersDataToLogIn(user.getEmail(), user.getPassword());
        mainPage.clickOnMainPageHeaderLogInButton();
        Assert.assertTrue("Email пользователя не совпадает", profilePage.userCorrectlyAuth(user.getEmail()));
    }

    @Test
    @DisplayName("Вход по кнопке 'Личный кабинет' в заголовке сайта")
    @Description("Проверка входа с главной страницы портала по кнопке 'Личный кабинет' в заголовке сайта")
    public void logInFromMainPageHeaderLogInButton(){
        mainPage.clickOnMainPageHeaderLogInButton();
        loginPage.fillUsersDataToLogIn(user.getEmail(), user.getPassword());
        mainPage.clickOnMainPageHeaderLogInButton();
        Assert.assertTrue("Email пользователя не совпадает", profilePage.userCorrectlyAuth(user.getEmail()));
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через кнопку 'Войти' в форме регистрации")
    public void logInFromRegistrationForm() {
        mainPage.clickOnMainPageLogInButton();
        loginPage.clickOnRegistrationButton();
        signUpPage.clickOnEnterButtonInSignUpPage();
        loginPage.fillUsersDataToLogIn(user.getEmail(), user.getPassword());
        mainPage.clickOnMainPageHeaderLogInButton();
        Assert.assertTrue("Email пользователя не совпадает", profilePage.userCorrectlyAuth(user.getEmail()));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через кнопку 'Войти' в форме восстановления пароля")
    public void logInFromRestorePassForm() {
        mainPage.clickOnMainPageLogInButton();
        loginPage.clickOnRecoverPasswordButton();
        restorePassPage.restoreEnterButtonClick();
        loginPage.fillUsersDataToLogIn(user.getEmail(), user.getPassword());
        mainPage.clickOnMainPageHeaderLogInButton();
        Assert.assertTrue("Email пользователя не совпадает", profilePage.userCorrectlyAuth(user.getEmail()));
    }

    @Test
    @DisplayName("Выход из аккаунта пользователя")
    @Description("Проверка выхода авторизованного пользователя из УЗ")
    public void logOutUserTest() {
        mainPage.clickOnMainPageLogInButton();
        loginPage.fillUsersDataToLogIn(user.getEmail(), user.getPassword());
        mainPage.clickOnMainPageHeaderLogInButton();
        profilePage.clickOnExitButton();
        Assert.assertTrue("Вы не вышли из своего личного кабинета", loginPage.userExitCheck());
    }
}
