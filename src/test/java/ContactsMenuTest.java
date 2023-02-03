import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactsMenuTest {
    By logoBrand = By.xpath("//a[@class='navbar-brand']//*[name()='svg']");
    By contactsButton = By.xpath("//ul[@class = 'navbar-nav mr-auto']//li[1]");
    By logout = By.xpath("//div[@class=\"my-2 my-lg-0\"]//button[2]");

    By account = By.cssSelector("[routerlink=\"/account\"]");
    By languageSelector = By.xpath("//select[@id = \"langSelect\"]");
    By english = By.xpath("//select//option[@value=\"en\"]");
    By russian = By.xpath("//select//option[@value=\"ru\"]");
    By german = By.xpath("//select//option[@value=\"de\"]");
    By ukraine = By.xpath("//select//option[@value=\"uk\"]");


    @Test
    public void ContactsMenu() {

        //Buttons "Contacts" and "Add new contact" , "Account"
        //driver.findElement(logoBrand).click();
        //driver.findElement(contactsButton).click();
        //driver.findElement(account).click();
        //driver.findElement(logout).click();


        //Language Selector
        /*
        driver.findElement(languageSelector).click();
        driver.findElement(english).click();
        driver.findElement(languageSelector).click();
        driver.findElement(russian).click();
        driver.findElement(languageSelector).click();
        driver.findElement(german).click();
        driver.findElement(languageSelector).click();
        driver.findElement(ukraine).click();

         */


    }

}
