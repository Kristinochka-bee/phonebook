package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ContactHelpers extends CommonHelpers {

    By searchContact = By.id("input-search-contact");
    By selectedNameInContactsTable = By.xpath("//div[@id='contacts-list']//button/b[normalize-space()='Cherri']");
    By contactsDetailsTable = By.xpath("//ul[@id='navbar-contact-details']");

    By buttonEdit = By.id("btn-edit-contact");
    By firstNameContactInfo = By.xpath("//input[@name='input-ec-firstName']");
    By lastNameContactInfo = By.xpath("//input[@name='input-ec-lastName']");
    By contactDescription = By.xpath("//textarea[@name='input-ec-description']");
    public By saveButton = By.xpath("//button[normalize-space()='Save']");

    By contactsLink = By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[1]");
    By selectChangedNameInContactsTable = By.xpath("//div[@id='contacts-list']//button/b[normalize-space()='Molly']");


    public void fillSearchField(String firstName) {
        driver.findElement(searchContact).isDisplayed();
        fillField(firstName, searchContact);
    }

    public void openContact() {
        driver.findElement(selectedNameInContactsTable).click();
        driver.findElement(contactsDetailsTable).isDisplayed();
        driver.findElement(buttonEdit).click();
    }

    public void clearInputFields() {
        driver.findElement(firstNameContactInfo).clear();
        driver.findElement(lastNameContactInfo).clear();
        driver.findElement(contactDescription).clear();
    }

    public void fillFieldsByAnotherDatas(String changedFirstName, String changedLastName, String changedDescription) {
        fillField(changedFirstName, firstNameContactInfo);
        fillField(changedLastName, lastNameContactInfo);
        fillField(changedDescription, contactDescription);
    }

    public void fillFieldsByProvData(String changedFirstName, String changedLastName, String changedDescription) {
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

    //после авторизации меняем язык на английский
    public ContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();

    }


}
