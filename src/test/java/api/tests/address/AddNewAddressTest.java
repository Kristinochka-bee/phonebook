package api.tests.address;

import api.enums.EndPoint;
import api.model.address.AddressDto;
import api.model.contact.ContactDto;
import api.tests.ApiBase;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@JsonInclude(JsonInclude.Include.NON_NULL)


public class AddNewAddressTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    AddressDto addressDto;
    Response response;
    int id;
    int wrongId;

    @BeforeMethod(onlyForGroups = {"positive"})//создание контакт
    public void precondition() {
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении
    }


    @AfterMethod(onlyForGroups = {"positive"})
    public void afterTest() {

        doDelete(EndPoint.DELETE_CONTACT, 200, id);
    }

    //posetive
    @Test(groups = {"positive"})
    public void addNewAddressTest() {
        addressDto = new AddressDto();
        addressDto.setCity(faker.address().city());
        addressDto.setCountry(faker.address().country());
        addressDto.setStreet(faker.address().streetAddress());
        addressDto.setZip(faker.address().zipCode());
        addressDto.setContactId(id);

        doPostReequest(EndPoint.ADD_NEW_ADDRESS, 201, addressDto);
    }

    //negative
    @Test
    public void addNewAddressWithWrongIdTest() {
        wrongId = getWrongId();
        addressDto = new AddressDto();
        addressDto.setCity(faker.address().city());
        addressDto.setCountry(faker.address().country());
        addressDto.setStreet(faker.address().streetAddress());
        addressDto.setZip(faker.address().zipCode());
        addressDto.setContactId(wrongId);

        response = doPostReequest(EndPoint.ADD_NEW_ADDRESS, 500, addressDto);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);
    }

    //negative
    @Test
    public void addNewAddressWithoutContactIdTest() {
        addressDto = new AddressDto();
        addressDto.setCity(faker.address().city());
        addressDto.setCountry(faker.address().country());
        addressDto.setStreet(faker.address().streetAddress());
        addressDto.setZip(faker.address().zipCode());

        response = doPostReequest(EndPoint.ADD_NEW_ADDRESS, 500, addressDto);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);
    }
}
