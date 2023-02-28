package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

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
    public void registerNewUserWithInvalidData() throws IOException, AWTException {
        String userData = faker.internet().password();
        String password = faker.internet().emailAddress();
        String expectedEmailErrorMessage = "Email must be a valid email address.";
        String expectedPasswordErrorMessage = "Password must be no longer than 20 characters.";

        app.getRegister().startRecording();
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        Assert.assertFalse(app.getRegister().isElementPresent(app.getRegister().errorMessageBlock));

        app.getRegister().checkErorMessage(app.getRegister().errorEmailMessageBlock, expectedEmailErrorMessage);
        app.getRegister().checkErorMessage(app.getRegister().errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);
        app.getRegister().startRecording();
    }

    @Test
    public void registerExistingUser() {
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists Login now?";

        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        app.getRegister().clickSignUpButton();

        app.getRegister().checkErorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage);

    }

}
