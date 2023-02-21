package api.tests.phone;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.phone.AddPhoneDto;
import api.model.phone.UpdatePhoneDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdatePhoneTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    AddPhoneDto addPhoneDto;
    UpdatePhoneDto updatePhoneDto;
    Response response;
    Response responseForPhone;
    int phoneId;
    int contactId;

    @BeforeMethod
    public void precondition() {
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        contactId = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении

        // добавляем телефон
        addPhoneDto = new AddPhoneDto();
        addPhoneDto.setCountryCode(faker.address().countryCode());
        addPhoneDto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        addPhoneDto.setContactId(contactId);

        //создаем контакт без ответа
        doPostReequest(EndPoint.ADD_NEW_PHONE, 201, addPhoneDto);
        // приходит ответ , нужно там достать id
        responseForPhone = doGetRequestWithParam(EndPoint.GET_LIST_OF_PHONES_BY_CONTACT_ID, 200, contactId);
        phoneId = responseForPhone.jsonPath().getInt("[0].id");

    }

    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, contactId);
    }

    @Test
    public void updatePhoneTest() {
        UpdatePhoneDto updatePhoneDto = new UpdatePhoneDto();

        updatePhoneDto.setId(phoneId); //?
        updatePhoneDto.setCountryCode(faker.address().countryCode());
        updatePhoneDto.setPhoneNumber(faker.phoneNumber().phoneNumber());
        updatePhoneDto.setContactId(contactId);

        doPutReequest(EndPoint.UPDATE_PHONE, 200, updatePhoneDto);


    }
}
