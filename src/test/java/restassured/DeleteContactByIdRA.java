package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdRA {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWljaGFlbCsxQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc4MjA3ODMwLCJpYXQiOjE2Nzc2MDc4MzB9.gApM305_ZPQfVVcvad9eNIBGHn0KFiMUBgckcpM51cA";
    String id;

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath="v1";

        int i = new Random().nextInt(1000) + 1000;
        ContactDto dto = ContactDto.builder()
                .name("Mia")
                .lastName("Dow")
                .email("mia" + i + "@mail.com")
                .phone("123412345" + i)
                .address("NY")
                .description("Friend").build();

      String message =  given()
                .header("Authorization",token)
                .body(dto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");
          String [] all = message.split(": ");
          id = all[1];
    }

    @Test
    public void deleteByIdSuccess(){
        given()
                .header("Authorization",token)
                .when()
                .delete("contacts/"+id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message",equalTo("Contact was deleted!"));
    }
    @Test
    public void deleteByIdWrongID(){
        given()
                .header("Authorization",token)
                .when()
                .delete("contacts/4a66bd9d-7af9-4575-993e-485de8409a7a")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message",containsString("not found in your contacts!"));
    }
}
