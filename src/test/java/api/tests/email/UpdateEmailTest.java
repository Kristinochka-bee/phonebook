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

public class UpdateEmailTest extends ApiBase {
    Faker faker = new Faker();
    int wrongContactId;
    int wrongEmailId;
    int contactId;
    int emailId;
    Response response;
    String email = faker.internet().emailAddress();

    String newEmail = faker.internet().emailAddress();
    Response responseForEmail;
    ContactDto contactDto;
    AddEmailDto addEmailDto;
    UpdateEmailDto emailDto;

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
    public void updateEmail() {
        emailDto = new UpdateEmailDto();
        emailDto.setId(emailId);
        emailDto.setEmail(newEmail);
        emailDto.setContactId(contactId);

        doPutReequest(EndPoint.UPDATE_EMAIL, 200, emailDto);
    }

    //negative
    @Test
    public void updateEmailWithWrongEmail() {
        wrongEmailId = getWrongId();
        emailDto = new UpdateEmailDto();
        emailDto.setId(wrongEmailId);
        emailDto.setEmail(newEmail);
        emailDto.setContactId(contactId);

        Response responseForUpdate = doPutReequest(EndPoint.UPDATE_EMAIL, 500, emailDto);
        Assert.assertEquals(responseForUpdate.jsonPath().getString("message"), ERROR_MESSAGE_FOR_EMAIL);
    }

    @Test
    public void updateEmailWithoutEmailId() {
        emailDto = new UpdateEmailDto();
        emailDto.setEmail(newEmail);
        emailDto.setContactId(contactId);
        Response responseForUpdate = doPutReequest(EndPoint.UPDATE_EMAIL, 500, emailDto);

        Assert.assertEquals(responseForUpdate.jsonPath().getString("message"), ERROR_MESSAGE_FOR_EMAIL);
    }

}
