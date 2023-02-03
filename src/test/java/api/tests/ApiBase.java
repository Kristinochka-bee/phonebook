package api.tests;

import api.enums.EndPoint;
import api.model.ContactDto;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;

    final String BASE_URI = "http://phonebook.telran-edu.de:8080";
    final String API_KEY = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InRlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MjEwNjk3ODI5Nn0.GM1wsoRV2QoAsD6wKmIk7N49DDpuCejK4BC9H9YItJvesH5vft8HO2uqTPnGQJwJ5oXKS2OILqP1yoanMnIMkA";

    //спецификация spec
    RequestSpecification spec = new RequestSpecBuilder() //собираем общие данные для всех будущих запросов
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("Access-Token", API_KEY) // для авторизации каждый раз
            .build();

    //POST
    public Response doPostReequest(EndPoint endPoint, Integer responseCode, Object dto) {   //Object dto - класс на кот будет формировать тело запросов (будут сод поля как и поля json)
        Response resp = RestAssured.given()
                .spec(spec)
                .body(dto)
                .when()
                .log().all()
                .post(endPoint.getValue())
                .then().log().all()
                .extract().response();
        resp.then().assertThat().statusCode(responseCode);
        return resp;

    }

    //DELETE
    public Response doDelete(EndPoint endPoint, Integer responseCode, int id) {
        Response resp = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam("id", id)  //в место айди подставляем айди
                .log().all()
                .delete(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
        resp.then().assertThat().statusCode(responseCode);
        return resp;

    }

    //Вынесенные в метод заполнение полей контакта
    public ContactDto createContact() {
        contactDto = new ContactDto();
        contactDto.setFirstName(faker.name().firstName());
        contactDto.setLastName(faker.name().lastName());
        contactDto.setDescription(faker.lorem().sentence());

        return contactDto;
    }
}
