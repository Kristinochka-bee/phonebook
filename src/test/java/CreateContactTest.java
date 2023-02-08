import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateContactTest extends ChangeLanguage {

    Faker faker = new Faker();

    By errorMessageBlock = By.xpath("//*[text()[contains(.,'Contact save fail')]]");
    //*[text()[contains(.,'Contact save fail')]]
    //*[contains(text(),'ABC')]
    //app-toasts[@class='ngb-toasts'])[1]
    By errorForm = By.id("form-error-firstName");

    By firstNameFill = By.id("form-name");
    By lastNameFill = By.id("form-lastName");
    By descriptionFill = By.id("form-about");

    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Molly", "Miller", "created manually"});
        list.add(new Object[]{"Bob", "Mr", "created 2 manually"});
        list.add(new Object[]{"Bobbi", "Mrs", "created 3 manually"});

        return list.iterator();
    }

    private void openAddNewContact() {
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    private void fillAddNewContactForm(String firstName, String lastName, String description) {
        fillField(firstName, By.xpath("//*[@role='dialog']//*[@placeholder='First name']"));
        fillField(lastName, By.xpath("//*[@role='dialog']//*[@placeholder='Last name']"));
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
    }

    private void saveNewContact() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    private void checkFieldsOnContactInfoAfterCreatedContact(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.id("contact-description"), description, "Actual description is not equal expected description");
    }

    private void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }

    private void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }

    private void clickOnInputFields() {
        driver.findElement(firstNameFill).click();
        driver.findElement(lastNameFill).click();
        driver.findElement(descriptionFill).click();
    }

    private void checkErrorMsg(String expectedErrorMessageOfForm) {
        String actualErrorMeassage = driver.findElement(errorForm).getText();
        Assert.assertEquals(actualErrorMeassage, expectedErrorMessageOfForm);
    }

    @Test(dataProvider = "newContact")
    public void createNewContact(String firstName, String lastName, String description) throws InterruptedException {
/*
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.internet().uuid();

 */
        Number expectedCountRow = 1;

        openAddNewContact();
        fillAddNewContactForm(firstName, lastName, description);
        saveNewContact();
        checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        goToContactPageAndFillFilterField(firstName);
        checkCountRows(expectedCountRow);
    }

    //negative test
    @Test
    public void createContactWithInvalidData() throws InterruptedException {
        String firstName = " ";
        String lastName = " ";
        String description = " ";
        String expectedErrorMessage = "Contact save fail";


        openAddNewContact();
        fillAddNewContactForm(firstName, lastName, description);
        saveNewContact();

        String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }


    @Test
    public void createContactWithEmptyFirstFill() throws InterruptedException {

        String expectedErrorMessageOfForm = "To add a contact, you must specify a name";

        openAddNewContact();

        clickOnInputFields();

        checkErrorMsg(expectedErrorMessageOfForm);

    }


}