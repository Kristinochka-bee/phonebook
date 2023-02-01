import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTest extends Login {

    By addNewContacktButton = By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[2]");
    By firstNameFill = By.xpath("//input[@id='form-name']");
    By lastNameFill = By.xpath("//input[@id=\"form-lastName\"]");
    By AboutFill = By.xpath("//input[@id=\"form-about\"]");
    By saveButton = By.cssSelector("[class='btn btn-primary']");
    By contactsLink = By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[1]");
    By searchContact = By.xpath("//input[@id='input-search-contact']");

    @Test
    public void createContact() { //сначала сделаем позитивный тест а потом негативный
        String firstName = "Mila";
        String lastName = "Belova";
        String About = "This contact added manually";


        Number expectedCountRow = 1;
        String expectedFirstName = "";
        String expectedLastName = "";
        String expecteAbout = "";


        //тесткейсы
        //click the button "Add new contact"
        driver.findElement(addNewContacktButton).click();
        Assert.assertTrue(isElementPresent(addNewContacktButton));

        //Fill input "First name*"
        fillField(firstName, firstNameFill);
        String actualFirstName = driver.findElement(firstNameFill).getText();
        Assert.assertEquals(actualFirstName, expectedFirstName);


        //Fill input "Last name*"
        fillField(lastName, lastNameFill);
        String actualLastName = driver.findElement(lastNameFill).getText();
        Assert.assertEquals(actualLastName, expectedLastName);


        //Fill input "About"
        fillField(About, AboutFill);
        String actualAbout = driver.findElement(AboutFill).getText();
        Assert.assertEquals(actualAbout, expecteAbout);

        //click on the button "Save"
        driver.findElement(saveButton).click();
        Assert.assertTrue(isElementPresent(saveButton));
        //Assert.assertTrue(isElementClickable(saveButton));      написать метод,что кнопка доступна Enable


        driver.findElement(contactsLink).click();
        Assert.assertTrue(isElementClickable(contactsLink));


        //Filter by creation name
        fillField(firstName, (searchContact));                    //Считает неправильно список выводимых имен
        Number actualCountRow = driver.findElements(By.id("contacts-list")).size(); //cчитаем кол.во строк выводимый поисковиком
        Assert.assertEquals(actualCountRow, expectedCountRow); //проверка актуального рез.та и ожидаемого*(1)

        //Expected result : Created contact show with correct data in the contact table


    }
}
