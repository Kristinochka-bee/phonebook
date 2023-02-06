import org.openqa.selenium.By;
import org.testng.Assert;
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


    private void fillSearchField(String firstName) {
        driver.findElement(searchContact).isDisplayed();
        fillField(firstName, searchContact);
    }

    private void openContact() {
        driver.findElement(selectedNameInContactsTable).click();
        driver.findElement(contactsDetailsTable).isDisplayed();
        driver.findElement(buttonEdit).click();
    }

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

    private void checkFieldsOnContactInfoAfterEditContactDatas(String changedFirstName, String changedLastName, String changedDescription) {
        String actualFirstName = driver.findElement(By.cssSelector("#contact-first-name")).getText();
        Assert.assertEquals(actualFirstName, changedFirstName);
        String actualLastName = driver.findElement(By.cssSelector("#contact-last-name")).getText();
        Assert.assertEquals(actualLastName, changedLastName);
        String actualDescription = driver.findElement(By.cssSelector("#contact-description")).getText();
        Assert.assertEquals(actualDescription, changedDescription);
    }


    @Test
    public void editContact() throws InterruptedException {
        String firstName = "Cherri";
        String lastName = "Mayer";
        String expectedDescription = "baboon";

        String changedFirstName = "Molly";
        String changedLastName = "Miller";
        String changedDescription = "player";


        fillSearchField(firstName);
        openContact();

        clearInputFields();
        fillFieldsByAnotherDatas(changedFirstName, changedLastName, changedDescription);

        driver.findElement(saveButton).click();
        Thread.sleep(1000);

        checkFieldsOnContactInfoAfterEditContactDatas(changedFirstName, changedLastName, changedDescription);


        //fill field search contact
        //click on the selected Name "Cherri Mayer"
        //Make Sure that Edit window is opened
        //Click on the button Edit
        //Change first Name
        //change last Name
        //Change description
        //Click on the button Save
        //Make sure the changes have been saved


    }


}
