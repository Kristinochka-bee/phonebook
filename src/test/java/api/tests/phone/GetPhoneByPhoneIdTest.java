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
    int id;
    int phoneId;
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
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении

        //добавляем телефон
        addPhoneDto = new AddPhoneDto();
        addPhoneDto.setCountryCode(countryCode);
        addPhoneDto.setPhoneNumber(phoneNumber);
        addPhoneDto.setContactId(id);

        doPostReequest(EndPoint.ADD_NEW_PHONE, 201, addPhoneDto);
        responseForPhone = doGetRequestWithParam(EndPoint.GET_LIST_OF_PHONES_BY_CONTACT_ID, 200, id);
        phoneId = responseForPhone.jsonPath().getInt("[0].id");

    }

    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, id);
    }


    @Test
    public void getPhoneByPhoneIdTest() {
        response = doGetRequestWithParam(EndPoint.GET_PHONE_BY_PHONE_ID, 200, phoneId);

        Assert.assertEquals(response.jsonPath().getInt("id"), phoneId); //Bag
        Assert.assertEquals(response.jsonPath().getString("phoneNumber"), getPhoneByPhoneIdDto.getPhoneNumber());
        Assert.assertEquals(response.jsonPath().getString("countryCode"), getPhoneByPhoneIdDto.getCountryCode());
        Assert.assertEquals(response.jsonPath().getInt("contactId"), getPhoneByPhoneIdDto.getContactId());

    }

    @Test
    public void getListOfPhonesByContactId() {
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].id"), phoneId);
        Assert.assertEquals(responseForPhone.jsonPath().getString("[0].phoneNumber"), phoneNumber);
        Assert.assertEquals(responseForPhone.jsonPath().getString("[0].countryCode"), countryCode);
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].contactId"), id);
    }

}