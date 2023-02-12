package api.tests.contact;

import api.enums.EndPoint;
import api.model.ContactDto;
import api.tests.ApiBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTest extends ApiBase {
    int wrongId;
    int id;
    Response response;
    ContactDto contactDto;

    @BeforeMethod(onlyForGroups = {"positive"})  //создание контакт
    public void precondition() {
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении
    }

    @Test(groups = ("positive")) //удаление контакта
    public void deleteContactTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test //(groups = ("negative"))
    public void deleteContactWithoutId() {
        wrongId = getWrongId();
        response = doDelete(EndPoint.DELETE_CONTACT, 500, wrongId);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE);
    }

}
