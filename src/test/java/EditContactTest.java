import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class EditContactTest extends ChangeLanguage {

    By searchContact = By.id("input-search-contact");
    By selectedNameInContactsTable = By.xpath("//div[@id='contacts-list']//button/b[normalize-space()='Cherri']");
    By contactsDetailsTable = By.xpath("//ul[@id='navbar-contact-details']");

    By buttonEdit = By.id("btn-edit-contact");
    By firstNameContactInfo = By.xpath("//input[@name='input-ec-firstName']");
    By lastNameContactInfo = By.xpath("//input[@name='input-ec-lastName']");
    By contactDescription = By.xpath("//textarea[@name='input-ec-description']");
    By saveButton = By.xpath("//button[normalize-space()='Save']");
    By contactsLink = By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[1]");
    By selectChangedNameInContactsTable = By.xpath("//div[@id='contacts-list']//button/b[normalize-space()='Molly']");

    private void clearInputFields() {
        driver.findElement(firstNameContactInfo).clear();
        driver.findElement(lastNameContactInfo).clear();
        driver.findElement(contactDescription).clear();
    }

    private void fillFieldsByAnotherDatas(String changedFirstName, String changedLastName, String changedDescription) {
        fillField(changedFirstName, firstNameContactInfo);
        fillField(changedLastName, lastNameContactInfo);
        fillField(changedDescription, contactDescription);
    }

    private void findContactInSearchFill(String firstName) {
        fillField(firstName, searchContact);
        driver.findElement(selectedNameInContactsTable).click();
        driver.findElement(contactsDetailsTable).isDisplayed();
        driver.findElement(buttonEdit).click();
    }

    private void findContactWithModifiedData(String changedFirstName) {
        driver.findElement(contactsLink).click();
        fillField(changedFirstName, searchContact);
        driver.findElement(selectChangedNameInContactsTable).click();
    }

    @Test
    public void editContact() throws InterruptedException {
        String firstName = "Cherri";
        String lastName = "Mayer";
        String expectedDescription = "baboon";

        String changedFirstName = "Molly";
        String changedLastName = "Miller";
        String changedDescription = "player";

        //fill field search contact
        //click on the selected Name "Cherri Mayer"
        //Make Sure that Edit window is opened
        //Click on the button Edit
        //Change first Name
        //change last Name
        //Change description
        //Click on the button Save
        //Make sure the changes have been saved
        //Click on the Contact button
        //enter just modifided name in search fill
        //click on the found name
        //Make Sure that name equal to our changes

        findContactInSearchFill(firstName);

        clearInputFields();
        fillFieldsByAnotherDatas(changedFirstName, changedLastName, changedDescription);

        driver.findElement(saveButton).click();
        Thread.sleep(1000);

        findContactWithModifiedData(changedFirstName);
    }


}
