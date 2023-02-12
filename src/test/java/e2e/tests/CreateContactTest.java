package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateContactTest extends TestBase {

    Faker faker = new Faker();


    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)

    public void createNewContact(String firstName, String lastName, String description) throws InterruptedException {

        Number expectedCountRow = 1;
        app.getLogin().login();
        app.getContact().changeLanguage();

        app.getContact().openAddNewContact();
        app.getContact().fillAddNewContactForm(firstName, lastName, description);
        app.getContact().saveNewContact();
        app.getContact().checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        app.getContact().goToContactPageAndFillFilterField(firstName);
        app.getContact().checkCountRows(expectedCountRow);
    }

    @Test(dataProvider = "newContactWithCSV", dataProviderClass = DataProviders.class)
    public void createNewContactDataProviderWithFileCSV(String firstName, String lastName, String description) throws InterruptedException {

        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getContact().changeLanguage();

        app.getContact().openAddNewContact();
        app.getContact().fillAddNewContactForm(firstName, lastName, description);
        app.getContact().saveNewContact();
        app.getContact().checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        app.getContact().goToContactPageAndFillFilterField(firstName);
        app.getContact().checkCountRows(expectedCountRow);
    }

    //negative test
    @Test
    public void createContactWithInvalidData() throws InterruptedException {
        String firstName = " ";
        String lastName = " ";
        String description = " ";
        String expectedErrorMessage = "Contact save fail";

        app.getLogin().login();
        app.getContact().changeLanguage();

        app.getContact().openAddNewContact();
        app.getContact().fillAddNewContactForm(firstName, lastName, description);
        app.getContact().saveNewContact();
        Thread.sleep(100);

        app.driver.findElement(By.xpath("//div[@class='toast-body'][1]"));
        app.getContact().checkItemText(By.xpath("//div[@class='toast-body'][1]"), expectedErrorMessage, "ok");

    }

    @Test
    public void createContactWithEmptyFirstFill() throws InterruptedException {

        app.getLogin().login();
        app.getContact().changeLanguage();


        String expectedErrorMessageOfForm = "To add a contact, you must specify a name";

        app.getContact().openAddNewContact();

        app.getContact().clickOnInputFields();

        app.getContact().checkErrorMsg(expectedErrorMessageOfForm);

    }


}