package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.helpers.EditeContactHelpers;
import org.testng.annotations.Test;

public class CommonContactTest extends TestBase {
    Faker faker = new Faker();

    @Test
    public void userCanCreateEditRemoveContact() throws InterruptedException {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().paragraph(1);
        Number expectedCountRow = 1;

        //create contact
        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContact();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);

        String newFirstName = faker.internet().uuid();
        String newLastName = faker.internet().uuid();
        String newDescription = faker.lorem().paragraph(1);

        //edit
        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(1);
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        app.getEditContact().clickOnEditButton();
        app.getEditContact().editeContactInfoForm(newFirstName, newLastName, newDescription);
        app.getEditContact().saveEditedContact();
        app.getEditContact().checkFieldsOnContactInfo(newFirstName, newLastName, newDescription);

        //remove
        EditeContactHelpers getRemoveContact = app.getEditContact();  // переназываем для понятности для удаления контакта
        getRemoveContact.goToContactPageAndFillFilterField(newFirstName);
        getRemoveContact.openRemoveContactDialog();
        getRemoveContact.removeContact();
        getRemoveContact.checkCountRows(0);

    }
}
