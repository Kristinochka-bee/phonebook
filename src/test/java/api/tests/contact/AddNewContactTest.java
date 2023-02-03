package api.tests.contact;

import api.enums.EndPoint;
import api.model.ContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AddNewContactTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    Response response;
    int id;


    //POST
    @Test
    public void createContactTest() {
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении
        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
    }

    //DELETE- подчищаем новосозданные тесты в этом классе
    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, id);
    }

}
