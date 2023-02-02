import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTest extends Login {

    By addNewContacktButton = By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[2]");
    By modalDialogCreateContact = By.id("modal-content");
    By firstNameFill = By.xpath("//input[@id='form-name']");
    By lastNameFill = By.xpath("//input[@id=\"form-lastName\"]");
    By AboutFill = By.xpath("//input[@id=\"form-about\"]");
    By saveButton = By.cssSelector("[class='btn btn-primary']");
    By contactsLink = By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[1]");
    By searchContact = By.id("input-search-contact");
    By createdFirstName = By.id("contact-first-name");
    By createdLastName = By.id("contact-last-name");
    By createdDescription = By.id("contact-description");

    Faker faker = new Faker();

    private void openAddNewContactDialog(By button, By presentform) {
        driver.findElement(addNewContacktButton).click();
        Assert.assertTrue(isElementPresent(modalDialogCreateContact));
    }

    private void fillAddNewContactForm(String firstName, String lastName, String About) {
        fillField(firstName, firstNameFill);
        fillField(lastName, lastNameFill);
        fillField(About, AboutFill);
    }

    private void checkFieldsOnContactInfoAfterCreatedContact(String firstName, String lastName, String About) {
        checkItemText(createdFirstName, firstName, "Actual error message is not equal expected");
        checkItemText(createdLastName, lastName, "Actual error message is not equal expected");
        checkItemText(createdDescription, About, "Actual error message is not equal expected");
    }

    private void contactsLinkButton() {
        driver.findElement(contactsLink).click();
        Assert.assertTrue(isElementClickable(contactsLink));
    }

    private void searchCreatedContactBySearchFill(String firstName, Integer expectedCountRow) {
        fillField(firstName, (searchContact));
        Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']")).size(); //cчитаем кол.во строк выводимый поисковиком
        Assert.assertEquals(actualCountRow, expectedCountRow); //проверка актуального рез.та и ожидаемого*(1)
    }

    @Test
    public void createContact() throws InterruptedException { //сначала сделаем позитивный тест а потом негативный
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();  //уникальный gener id
        String About = faker.internet().uuid();
        Integer expectedCountRow = 1;


        //тесткейсы
        //click the button "Add new contact"
        openAddNewContactDialog(addNewContacktButton, modalDialogCreateContact);

        fillAddNewContactForm(firstName, lastName, About);

        //click on the button "Save"
        driver.findElement(saveButton).click();


        Thread.sleep(1000);  //после клика на кнопку нужно "ожидание", чтоб не падал тест

        //Edid form проверка созданных полей / метод вынесен в TestBase
        checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, About);

        //переход на главную страницу
        contactsLinkButton();

        //Filter by creation name
        searchCreatedContactBySearchFill(firstName, expectedCountRow);


        //Expected result : Created contact show with correct data in the contact table


    }


}
