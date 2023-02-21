package api.tests.phone;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.phone.AddPhoneDto;
import api.tests.ApiBase;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class AddNewPhoneTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    AddPhoneDto phoneDto;
    Response response;
    int id;
    int wrongId;

    //creating contact
    @BeforeMethod(onlyForGroups = {"positive"})//создание контакт
    public void precondition() {
        contactDto = createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении
    }

    //delete contact
    @AfterMethod(onlyForGroups = {"positive"})
    public void afterTest() {

        doDelete(EndPoint.DELETE_CONTACT, 200, id);
    }

    //posetive
    @Test(groups = {"positive"})
    public void addNewPhoneTest() {
        phoneDto = new AddPhoneDto();
        phoneDto.setCountryCode(faker.address().countryCode());
        phoneDto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        phoneDto.setContactId(id);

        doPostReequest(EndPoint.ADD_NEW_PHONE, 201, phoneDto);
    }

    //negative
    @Test
    public void addNewPhoneWithWrongIdTest() {
        wrongId = getWrongId();
        phoneDto = new AddPhoneDto();
        phoneDto.setCountryCode(faker.address().countryCode());
        phoneDto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        phoneDto.setContactId(wrongId);

        response = doPostReequest(EndPoint.ADD_NEW_PHONE, 500, phoneDto);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);


    }

    //negative
    @Test
    public void addNewPhoneWithoutContactIdTest() {
        phoneDto = new AddPhoneDto();
        phoneDto.setCountryCode(faker.address().countryCode());
        phoneDto.setPhoneNumber(faker.phoneNumber().phoneNumber());

        response = doPostReequest(EndPoint.ADD_NEW_PHONE, 500, phoneDto);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);

    }

    //negative
    @Test
    public void addNewPhoneWithoutCountryCodeTest() {
        phoneDto = new AddPhoneDto();
        phoneDto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        phoneDto.setContactId(id);

        doPostReequest(EndPoint.ADD_NEW_PHONE, 400, phoneDto);
    }

}
