package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class EditContactTest extends TestBase {

    @Test(dataProvider = "editWithCSV", dataProviderClass = DataProviders.class)
    public void editContact(String lastName, String expectedDescription) throws InterruptedException {
        String firstName = "Cherri";
        app.getLogin().login();
        app.getContact().changeLanguage();
        app.getContact().fillSearchField(firstName);
        app.getContact().openContact();
        app.getContact().clearInputFields();
        app.getContact().fillFieldsByProvData(firstName, lastName, expectedDescription);
        app.driver.findElement(app.getContact().saveButton).click();

    }

}
