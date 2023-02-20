package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditeContactHelpers extends ContactHelpers {

    public EditeContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void clickOnEditButton() {
        driver.findElement(buttonEdit).click();
    }

    public void openEditForm() {
        clickOnVisibleElement(By.id("btn-edit-contact"));
    }

    public void editeLastNameAndDescription(String lastName, String description) {
        driver.findElement(lastNameContactInfo).clear();
        driver.findElement(contactDescription).clear();
        fillField(lastName, lastNameContactInfo);
        fillField(description, contactDescription);
    }

    public void saveEditedContact() {
        clickOnVisibleElement(saveButton);
    }

    public void editeContactInfoForm(String firstName, String lastName, String description) {
        fillField(firstName, By.name(("input-ec-firstName")));
        editeLastNameAndDescription(lastName, description);
    }
}
