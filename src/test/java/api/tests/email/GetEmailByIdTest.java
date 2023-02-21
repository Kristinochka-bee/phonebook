package api.tests.email;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.email.AddEmailDto;
import api.model.email.UpdateEmailDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetEmailByIdTest extends ApiBase {
    Faker faker = new Faker();
    int wrongId;
    int contactId;
    int emailId;
    Response response;
    String email = faker.internet().emailAddress();
    Response responseForEmail;
    ContactDto contactDto;
    AddEmailDto addEmailDto;

    @BeforeMethod
    public void precondition() {
        //создание контакт
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        contactId = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении

        //добавляем емайл
        addEmailDto = new AddEmailDto();
        addEmailDto.setEmail(email);
        addEmailDto.setContactId(contactId);

        doPostReequest(EndPoint.ADD_NEW_EMAIL, 201, addEmailDto); //создаем контакт без ответа
        responseForEmail = doGetRequestWithParam(EndPoint.GET_LIST_OF_EMAILS_BY_CONTACT_ID, 200, contactId);  // приходит ответ , нужно там достать id
        emailId = responseForEmail.jsonPath().getInt("[0].id");
    }

    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, contactId);
    }


    @Test
    public void getEmailByEmailIdTest() {
        response = doGetRequestWithParam(EndPoint.GET_EMAIL_BY_EMAIL_ID, 200, emailId);
        UpdateEmailDto emailDto = response.as(UpdateEmailDto.class); //response will be written into this fields

        Assert.assertEquals(emailDto.getId(), emailId);
        Assert.assertEquals(emailDto.getEmail(), email);
        Assert.assertEquals(emailDto.getContactId(), contactId);


    }

    @Test
    public void getListOfEmailsByContactId() {
        Assert.assertNotEquals(responseForEmail.jsonPath().getInt("[0].id"), null);
        Assert.assertEquals(responseForEmail.jsonPath().getString("[0].email"), email);
        Assert.assertEquals(responseForEmail.jsonPath().getInt("[0].contactId"), contactId);

    }


}
