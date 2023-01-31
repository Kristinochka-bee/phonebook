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

    By errorPasswordMaxLengthMessageBlock = By.id("password-error-maxlength");

    By errorEmailMessageBlock = By.id("email-error-invalid");

    Faker faker = new Faker();   //создали класс , через точку вызовем его методы ниже *рандом

    private void goToRegistrationPage() {
        Assert.assertTrue(isElementPresent(loginForm));
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
    }

    private void fillRegistrationForm(String userData, String password) {
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPassField);
    }

    private void clickSignUpButton() {
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }

    private void checkErorMessage(By locator, String expectedErrorMessage) {
        String actualErrorMessage = driver.findElement(locator).getText();
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }


    @Test
    public void registerNewUserWithValidData() {
        //Arrange подготовка к тестированию
        String userData = faker.internet().emailAddress(); //вызываем метод у faker для 3-х полей с названием userData. Будут генерироваться эмайлы , пароли и confirmPassField рандомно
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg"; //как должно быть

        //Act само действие
        // Используем локаторы, кот вынесли вверх ранее. В последовательности тестирования
        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillRegistrationForm(userData, password);
        clickSignUpButton();

        //Assert сравнение,  проверка Позитивные тесты
        checkErorMessage(errorMessageBlock, expectedErrorMessage);


    }


    @Test
    public void registerNewUserWithInvalidData() {
        //Arrange подготовка к тестированию
        String userData = faker.internet().password(); //вызываем метод у faker для 3-х полей с названием userData. Будут генерироваться эмайлы , пароли и confirmPassField рандомно
        String password = faker.internet().emailAddress(); //поменяли местами пароль и емайл
        String expectedEmailErrorMessage = "Email must be a valid email address."; //как должно быть
        String expectedPasswordErrorMessage = "Password must be no longer than 20 characters."; //как должно быть


        //Act само действие
        // Используем локаторы, кот вынесли вверх ранее. В последовательности тестирования
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        Assert.assertFalse(isElementPresent(errorMessageBlock)); // фактический результат отображаемой ошибки

        //Assert
        checkErorMessage(errorEmailMessageBlock, expectedEmailErrorMessage);
        checkErorMessage(errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);

    }

    @Test
    public void registerExistingUser() {
        //Arrange подготовка к тестированию

        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists Login now?";

        //Act само действие
        // Используем локаторы, кот вынесли вверх ранее. В последовательности тестирования
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        clickSignUpButton();


        //Assert
        checkErorMessage(errorMessageBlock, expectedErrorMessage);

    }

}
