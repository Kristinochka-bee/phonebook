package api.tests.address;

import api.enums.EndPoint;
import api.model.address.AddressDto;
import api.model.address.UpdateAddressDto;
import api.model.contact.ContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateAddressTest extends ApiBase {
    Faker faker = new Faker();
    Response response;
    Response responseForAddress;
    int addressId;
    int contactId;
    ContactDto contactDto;
    AddressDto addressDto;
    UpdateAddressDto updateAddressDto;

    @BeforeMethod
    public void precondition() {
        //создание контакт
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        contactId = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении

        addressDto = new AddressDto();
        addressDto.setCity(faker.address().city());
        addressDto.setCountry(faker.address().country());
        addressDto.setStreet(faker.address().streetAddress());
        addressDto.setZip(faker.address().zipCode());
        addressDto.setContactId(contactId);

        doPostReequest(EndPoint.ADD_NEW_ADDRESS, 201, addressDto);
        responseForAddress = doGetRequestWithParam(EndPoint.GET_LIST_OF_ADDRESSES_BY_CONTACT_ID, 200, contactId);
        addressId = responseForAddress.jsonPath().getInt("[0].id");

    }

    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, contactId);
    }

    @Test
    public void updateAddressTest() {
        updateAddressDto = new UpdateAddressDto();
        updateAddressDto.setId(addressId);
        updateAddressDto.setCity(faker.address().city());
        updateAddressDto.setCountry(faker.address().country());
        updateAddressDto.setStreet(faker.address().streetAddress());
        updateAddressDto.setZip(faker.address().zipCode());
        updateAddressDto.setContactId(contactId);

        doPutReequest(EndPoint.UPDATE_ADDRESS, 200, updateAddressDto);
        response = doGetRequestWithParam(EndPoint.GET_ADDRESS_BY_ADDRESS_ID, 200, addressId);

        //Assert.assertEquals(response.jsonPath().getInt("id"), addressId);//Bag
        Assert.assertEquals(response.jsonPath().getString("city"), updateAddressDto.getCity());
        Assert.assertEquals(response.jsonPath().getString("country"), updateAddressDto.getCountry());
        Assert.assertEquals(response.jsonPath().getString("street"), updateAddressDto.getStreet());
        Assert.assertEquals(response.jsonPath().getString("zip"), updateAddressDto.getZip());
        Assert.assertEquals(response.jsonPath().getInt("contactId"), updateAddressDto.getContactId());


    }

}
