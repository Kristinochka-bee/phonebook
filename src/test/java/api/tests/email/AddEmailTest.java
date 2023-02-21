package api.tests.email;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.email.AddEmailDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddEmailTest extends ApiBase {
    Faker faker = new Faker();
    int wrongId;
    int id;
    Response response;
    ContactDto contactDto;
    AddEmailDto addEmailDto;

    @BeforeMethod(onlyForGroups = {"positive", "correctId"})//создание контакт
    public void precondition() {
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении
    }

    @AfterMethod(onlyForGroups = {"positive", "correctId"})
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, id);
    }

    //positive
    @Test(groups = {"positive"})
    public void addEmailTest() {
        // sending body request
        addEmailDto = new AddEmailDto();
        addEmailDto.setEmail(faker.internet().emailAddress());
        addEmailDto.setContactId(id);
        //test
        doPostReequest(EndPoint.ADD_NEW_EMAIL, 201, addEmailDto);

    }

    //negative
    @Test(groups = {"correctId"})
    public void addEmailWithoutEmail() {
        addEmailDto = new AddEmailDto();
        addEmailDto.setContactId(id);

        doPostReequest(EndPoint.ADD_NEW_EMAIL, 400, addEmailDto);
    }

    @Test
    public void addEmailWithWrongId() {
        wrongId = getWrongId();
        addEmailDto = new AddEmailDto();
        addEmailDto.setEmail(faker.internet().emailAddress());
        addEmailDto.setContactId(wrongId);

        response = doPostReequest(EndPoint.ADD_NEW_EMAIL, 500, addEmailDto);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);
    }

    @Test
    public void addEmailWithEmptyBody() {
        String body = "{}";
        doPostReequest(EndPoint.ADD_NEW_EMAIL, 400, body);
    }

}
