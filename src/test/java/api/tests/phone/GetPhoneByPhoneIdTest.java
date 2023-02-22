package api.tests.phone;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.phone.AddPhoneDto;
import api.model.phone.GetPhoneByPhoneIdDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetPhoneByPhoneIdTest extends ApiBase {
    Faker faker = new Faker();
    int phoneId;
    int contactId;
    Response response;
    Response responseForPhone;
    ContactDto contactDto;
    AddPhoneDto addPhoneDto;
    GetPhoneByPhoneIdDto getPhoneByPhoneIdDto;
    String countryCode = faker.address().countryCode();
    String phoneNumber = faker.phoneNumber().phoneNumber();

    @BeforeMethod
    public void precondition() {
        //создание контакт
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        contactId = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении

        //добавляем телефон
        addPhoneDto = new AddPhoneDto();
        addPhoneDto.setCountryCode(countryCode);
        addPhoneDto.setPhoneNumber(phoneNumber);
        addPhoneDto.setContactId(contactId);

        doPostReequest(EndPoint.ADD_NEW_PHONE, 201, addPhoneDto);
        responseForPhone = doGetRequestWithParam(EndPoint.GET_LIST_OF_PHONES_BY_CONTACT_ID, 200, contactId);
        phoneId = responseForPhone.jsonPath().getInt("[0].id");

    }

    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, contactId);
    }

    @Test
    public void getPhoneByPhoneIdTest() {
        response = doGetRequestWithParam(EndPoint.GET_PHONE_BY_PHONE_ID, 200, phoneId);
        GetPhoneByPhoneIdDto phoneDto = response.as(GetPhoneByPhoneIdDto.class);

        //Assert.assertEquals(phoneDto.getId(), phoneId);      Bag
        Assert.assertEquals(phoneDto.getPhoneNumber(), phoneNumber);
        Assert.assertEquals(phoneDto.getCountryCode(), countryCode);
        Assert.assertEquals(phoneDto.getContactId(), contactId);

    }

    @Test //??
    public void getListOfPhonesByContactId() {
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].id"), contactId);
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].phoneNumber"), phoneNumber);
        Assert.assertEquals(responseForPhone.jsonPath().getString("[0].countryCode"), countryCode);
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].contactId"), contactId);
    }

}