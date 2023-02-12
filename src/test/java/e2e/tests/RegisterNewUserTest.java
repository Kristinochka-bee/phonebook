package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends TestBase {
    Faker faker = new Faker();

    @Test
    public void registerNewUserWithValidData() {
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";

        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        app.getRegister().clickSignUpButton();
        app.getRegister().checkErorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage);

    }

    @Test
    public void registerNewUserWithInvalidData() {
        String userData = faker.internet().password(); //вызываем метод у faker для 3-х полей с названием userData. Будут генерироваться эмайлы , пароли и confirmPassField рандомно
        String password = faker.internet().emailAddress(); //поменяли местами пароль и емайл
        String expectedEmailErrorMessage = "Email must be a valid email address."; //как должно быть
        String expectedPasswordErrorMessage = "Password must be no longer than 20 characters."; //как должно быть

        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        Assert.assertFalse(app.getRegister().isElementPresent(app.getRegister().errorMessageBlock)); // фактический результат отображаемой ошибки

        app.getRegister().checkErorMessage(app.getRegister().errorEmailMessageBlock, expectedEmailErrorMessage);
        app.getRegister().checkErorMessage(app.getRegister().errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);

    }

    @Test
    public void registerExistingUser() {
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists UA.e2e.ChangeLanguage.e2e.helpers.Login now?";

        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        app.getRegister().clickSignUpButton();

        app.getRegister().checkErorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage);

    }

}
