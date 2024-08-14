package site.nomoreparties.stellarburgers.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import site.nomoreparties.stellarburgers.models.UserModel;

import static io.restassured.RestAssured.given;
import static site.nomoreparties.stellarburgers.commons.EpAndApi.*;

public class UserSteps {

    @Step("Данные пользователя")
    public void userData(UserModel user) {
        user.setEmail((RandomStringUtils.randomAlphabetic(8)
                .toLowerCase()) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(14));
        user.setName(RandomStringUtils.randomAlphabetic(9));

    }

    @Step("Извлечение токена авторизации")
    public void takingAccessToken(UserModel user) {
        String accessToken = loginUser(user).extract()
                .body().path("accessToken");
        user.setAccessToken(accessToken);
    }

    @Step("Создать нового пользователя")
    public ValidatableResponse createUser(UserModel user) {
         return given()
                .contentType(ContentType.JSON)
                .baseUri(TEST_STAND)
                .body(user)
                .when()
                .post(USER_CREATE)
                .then();
    }

    @Step("Авторизация пользователя")
    public static ValidatableResponse loginUser(UserModel user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(TEST_STAND)
                .body(user)
                .when()
                .post(USER_LOGIN)
                .then();
    }

    @Step("Удалить пользователя")
    public void deleteUser(UserModel user) {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", user.getAccessToken())
                .baseUri(TEST_STAND)
                .when()
                .delete(USER_DATA)
                .then();
    }
}
