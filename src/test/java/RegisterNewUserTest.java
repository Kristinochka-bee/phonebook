import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
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
    By errorMessageBlock = By.id("error-message");
    Faker faker = new Faker();   //создали класс , через точку вызовем его методы ниже *рандом


    @Test
    public void registerNewUser() {
        //Arrange подготовка к тестированию
        String userData = faker.internet().emailAddress(); //вызываем метод у faker для 3-х полей с названием userData. Будут генерироваться эмайлы , пароли и confirmPassField рандомно
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg"; //как должно быть

        //Act само действие
        // Используем локаторы, кот вынесли вверх ранее. В последовательности тестирования
        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPassField);
        driver.findElement(loginButton).click();
        String actualErrorMessage = driver.findElement(errorMessageBlock).getText(); // фактический результат

        //Assert сравнение,  проверка
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }


}