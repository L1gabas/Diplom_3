import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageObjects.SignUpPage;

import static site.nomoreparties.stellarburgers.commons.EpAndApi.*;

public class CreateUserTest extends CommonsForTests{
    private SignUpPage signUpPage;

    @Before
    public void setUpsForTest(){
        signUpPage = new SignUpPage(driver);
        steps.userData(user);
        steps.createUser(user);
        driver.get(TEST_STAND);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка создания пользователя с валидными данными")
    public void successUserCreateTest(){
        mainPage.clickOnMainPageLogInButton();
        loginPage.clickOnRegistrationButton();
        signUpPage.fillUsersData(user);
        signUpPage.clickOnRegistrationInSignUpPageButton();
        loginPage.refreshLoginPage();
        loginPage.fillUsersDataToLogIn(user);
        mainPage.clickOnMainPageHeaderLogInButton();
        Assert.assertTrue("Email пользователя не совпадает", profilePage.userCorrectlyAuth(user));
    }

    @Test
    @DisplayName("Отображение ошибки при не валидных данных")
    @Description("Тест для проверки невалидных данных, при заполнении поля 'пароль'")
    public void unSuccessUserCreateTestInvalidPass(){
        mainPage.clickOnMainPageLogInButton();
        loginPage.clickOnRegistrationButton();
        signUpPage.incorrectPassInput();
        signUpPage.clickOnRegistrationInSignUpPageButton();
        Assert.assertTrue("Сообщение не отображено", signUpPage.incorrectPassAndMessageCheck());
    }

}
