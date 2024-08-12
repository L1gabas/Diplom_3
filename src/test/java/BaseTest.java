import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.commons.WebDriverFactory;
import site.nomoreparties.stellarburgers.models.UserModel;
import site.nomoreparties.stellarburgers.page.objects.LoginPage;
import site.nomoreparties.stellarburgers.page.objects.MainPage;
import site.nomoreparties.stellarburgers.page.objects.ProfilePage;
import site.nomoreparties.stellarburgers.steps.UserSteps;

public class BaseTest {

    protected static WebDriver driver;
    protected static UserModel user;
    protected static UserSteps steps;
    protected static LoginPage loginPage;
    protected static MainPage mainPage;
    protected static ProfilePage profilePage;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver();
        user = new UserModel();
        steps = new UserSteps();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @After
    public void tearDown(){
        steps.takingAccessToken(user);

        if (user.getAccessToken() != null){
            steps.deleteUser(user);
        } else {
            System.out.println("Токен null");
        }
        driver.quit();
    }
}
