package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactHelpers extends CommonHelpers {

    //после авторизации меняем язык на английский
    public ContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();

    }

    By searchContact = By.id("input-search-contact");
    By selectedNameInContactsTable = By.xpath("//div[@id='contacts-list']//button/b[normalize-space()='Cherri']");
    By contactsDetailsTable = By.xpath("//ul[@id='navbar-contact-details']");

    By buttonEdit = By.id("btn-edit-contact");
    By firstNameContactInfo = By.xpath("//input[@name='input-ec-firstName']");
    By lastNameContactInfo = By.xpath("//input[@name='input-ec-lastName']");
    By contactDescription = By.xpath("//textarea[@name='input-ec-description']");
    public By saveButton = By.xpath("//button[normalize-space()='Save']");


    public void fillSearchField(String firstName) {
        driver.findElement(searchContact).isDisplayed();
        fillField(firstName, searchContact);
    }

    public void openContact() {
        clickOnVisibleElement(By.xpath("//*[@id='contacts-list']//*[@class='list-group']"));
    }

    public void openRemoveContactDialog() {
        openDialog(By.xpath("//*[@id='contacts-list']//*[@class='list-group-item']/img"));
    }

    public void removeContact() {
        clickOnVisibleElement(By.id("check-box-remove-contact"));
        clickOnVisibleElement(By.id("submit-remove"));
        setWait().until(ExpectedConditions.invisibilityOfElementLocated  //избавляемся от Threadsleep
                (By.xpath("//*[@role='dialog']")));
    }

    public void checkFieldsOnContactInfo(String firstName, String lastName, String description) throws InterruptedException {
        checkItemText(By.xpath("//*[@id='edit-contact-form']//*[@id='contact-first-name']"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.xpath("//*[@id='edit-contact-form']//*[@id='contact-last-name']"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.xpath("//*[@id='edit-contact-form']//*[@id='contact-description']"), description, "Actual description is not equal expected description");
    }


    public void clearInputFields() {
        driver.findElement(firstNameContactInfo).clear();
        driver.findElement(lastNameContactInfo).clear();
        driver.findElement(contactDescription).clear();
    }

    public void clearInputFieldsLastNameAndDescription() {
        driver.findElement(lastNameContactInfo).clear();
        driver.findElement(contactDescription).clear();
    }

    public void fillFieldsByAnotherDatas(String changedFirstName, String changedLastName, String changedDescription) {
        fillField(changedFirstName, firstNameContactInfo);
        fillField(changedLastName, lastNameContactInfo);
        fillField(changedDescription, contactDescription);
    }

    public void fillFieldsByProvData(String changedLastName, String changedDescription) {
        fillField(changedLastName, lastNameContactInfo);
        fillField(changedDescription, contactDescription);
    }


}
