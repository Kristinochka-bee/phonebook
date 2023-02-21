package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class EditContactTest extends TestBase {

    @Test(dataProvider = "editWithCSV", dataProviderClass = DataProviders.class)
    public void editContact(String lastName, String description) throws InterruptedException {
        String firstName = "aeec8e16-3bf8-4ada-92c3-b946bf474c47";
        app.getLogin().login();
        app.getEditContact().changeLanguage();
        app.getEditContact().fillSearchField(firstName);
        app.getEditContact().checkCountRows(1);
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        app.getEditContact().clickOnEditButton();
        Thread.sleep(1000);
        /*
        app.getContact().clearInputFieldsLastNameAndDescription();
        app.getContact().fillFieldsByProvData(lastName, expectedDescription);

         */
        app.getEditContact().editeLastNameAndDescription(lastName, description);
        Thread.sleep(1000);
        //app.getContact().checkFieldsOnContactInfoAfterEditContactDatas(lastName, expectedDescription);
        //app.driver.findElement(app.getContact().saveButton).click();
        app.getEditContact().saveEditedContact();
        Thread.sleep(1000);
        app.getEditContact().checkFieldsOnContactInfo(firstName, lastName, description);

    }

}
