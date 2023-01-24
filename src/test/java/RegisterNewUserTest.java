import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends TestBase {  //этот класс относится только к регистрации нового пользователя кот унаследован от класса TestBase

    //Вынесенные локаторы
    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPassField = By.cssSelector("[placeholder=\"Confirm Password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    Faker faker = new Faker();   //создали класс , через точку вызовем его методы ниже *рандом


    @Test
    public void registerNewUser() {
        String userData = faker.internet().emailAddress(); //вызываем метод у faker для 3-х полей с названием userData. Будут генерироваться эмайлы , пароли и confirmPassField рандомно

        // Используем локаторы, кот вынесли вверх ранее. В последовательности тестирования
        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(userData, passwordField);
        fillField(userData, confirmPassField);
        driver.findElement(loginButton).click();
    }


}
