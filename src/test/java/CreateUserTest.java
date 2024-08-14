import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.models.UserModel;
import site.nomoreparties.stellarburgers.pageobjects.SignUpPage;

import static site.nomoreparties.stellarburgers.commons.EpAndApi.*;

public class CreateUserTest extends BaseTest {
    UserModel user = new UserModel();
    String userName = RandomStringUtils.randomAlphabetic(10);
    String userEmail = RandomStringUtils.randomAlphabetic(10)
            .toLowerCase() + "@yandex.ru";
    String userPass = RandomStringUtils.randomAlphabetic(10);
    private SignUpPage signUpPage;

    @Before
    public void setUpsForTest(){
        signUpPage = new SignUpPage(driver);
        steps.userData(user);
        driver.get(TEST_STAND);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка создания пользователя с валидными данными")
    public void successUserCreateTest(){
        mainPage.clickOnMainPageLogInButton();
        loginPage.clickOnRegistrationButton();
        signUpPage.fillUsersData(userName, userEmail, userPass);
        signUpPage.clickOnRegistrationInSignUpPageButton();
        loginPage.refreshLoginPage();
        loginPage.fillUsersDataToLogIn(userEmail, userPass);
        mainPage.clickOnMainPageHeaderLogInButton();
        Assert.assertTrue("Email пользователя не совпадает", profilePage.userCorrectlyAuth(userEmail));
    }

    @Test
    @DisplayName("Отображение ошибки при не валидных данных")
    @Description("Тест для проверки невалидных данных, при заполнении поля 'пароль'")
    public void unSuccessUserCreateTestInvalidPass(){
        mainPage.clickOnMainPageLogInButton();
        loginPage.clickOnRegistrationButton();
        signUpPage.fillUsersData(userName, userEmail, "plz");
        signUpPage.clickOnRegistrationInSignUpPageButton();
        Assert.assertTrue("Сообщение не отображено", signUpPage.incorrectPassAndMessageCheck());
    }

}
