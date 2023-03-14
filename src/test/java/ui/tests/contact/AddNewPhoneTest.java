package ui.tests.contact;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.TestBase;
import ui.page.contact.ContactPage;
import ui.page.contact.PhoneTab;
import ui.page.login.LoginPage;

public class AddNewPhoneTest extends TestBase {
    int id;
    Response response;
    ContactDto contactDto;
    LoginPage loginPage;
    ContactPage contactPage;
    PhoneTab phoneTab;
    String phoneNumber = faker.number().digits(8).toString();

    @BeforeMethod  //создание контакт
    public void precondition() {
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении
        loginPage = new LoginPage(driver);
        loginPage.getLogin(EMAIL, PASSWORD);
    }

    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, id);
    }


    @Test
    public void addNewPhoneTest() throws InterruptedException {
        contactPage = new ContactPage(driver);
        contactPage.getPage(contactPage.getBASE_PAGE_URL() + id);
        Thread.sleep(1000);
        contactPage.click(contactPage.getPhoneTab());
        phoneTab = new PhoneTab(driver);
        phoneTab.click(phoneTab.getAddPhoneNumber());
        phoneTab.selectRandomOption(phoneTab.getCountryCodeSelect());
        phoneTab.inputText(phoneTab.getPhoneNumberInput(), phoneNumber);
        phoneTab.click(phoneTab.getSaveButton());

        Assert.assertEquals(phoneTab.getText(phoneTab.getPhoneNumberField()), phoneNumber);


    }


}
