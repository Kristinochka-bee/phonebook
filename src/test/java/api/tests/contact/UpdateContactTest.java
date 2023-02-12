package api.tests.contact;

import api.enums.EndPoint;
import api.model.ContactDto;
import api.model.UpdateContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateContactTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    // UpdateContactDto updateContactDto;
    Response response;
    int id;


    @BeforeMethod
    public void precondition() {
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении
    }


    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test
    public void updateContactTest() {
        UpdateContactDto updateContactDto = new UpdateContactDto();

        updateContactDto.setId(id);
        updateContactDto.setFirstName(faker.name().firstName());
        updateContactDto.setLastName(faker.name().lastName());
        updateContactDto.setDescription(faker.lorem().sentence(4));

        doPutReequest(EndPoint.UPDATE_CONTACT, 200, updateContactDto);
        response = doGetRequestWithParam(EndPoint.GET_CONTACT_BY_CONTACT_ID, 200, id);

        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        Assert.assertEquals(response.jsonPath().getString("firstName"), updateContactDto.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), updateContactDto.getLastName());
        Assert.assertEquals(response.jsonPath().getString("description"), updateContactDto.getDescription());

    }
}
