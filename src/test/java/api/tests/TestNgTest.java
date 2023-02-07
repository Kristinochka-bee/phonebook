package api.tests;

import api.enums.EndPoint;
import api.model.ContactDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNgTest extends ApiBase {
    ContactDto contactDto;
    Response response;
    int id;

    @BeforeMethod
    public void precondition() {
        contactDto = createContact();  //заполняем поля с помощью метода createContact();
        response = doPostReequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");  //достаем id данного контакта, кот будем использывать при удалении
        System.out.println("Этот метод будет выполнен перед каждым тестом");
    }

    @AfterMethod
    public void afterTest() {
        doDelete(EndPoint.DELETE_CONTACT, 200, id);
        System.out.println("Этот метод будет выполнен после каждого теста");

    }


    @Test
    public void firstName() {
        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
    }
}
