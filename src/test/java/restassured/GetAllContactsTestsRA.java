package restassured;

import com.jayway.restassured.RestAssured;
import dto.AllContactsDto;
import dto.ContactDto;
import org.jetbrains.annotations.TestOnly;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllContactsTestsRA {
String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWljaGFlbCsxQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc4MjA3ODMwLCJpYXQiOjE2Nzc2MDc4MzB9.gApM305_ZPQfVVcvad9eNIBGHn0KFiMUBgckcpM51cA";

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath="v1";
    }

    @Test
    public void getAllContactsSuccess(){
      AllContactsDto all =  given()
                .header("Authorization",token)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .as(AllContactsDto.class);
        List<ContactDto> contacts = all.getContacts();
        for (ContactDto contactDto:contacts){
            System.out.println(contactDto.getId());
            System.out.println("******");
        }

    }

    @Test
    public void getAllContactsNegative(){
        given()
                .header("Authorization","hjhk9")
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("error",equalTo("Unauthorized"));
    }
}
//b9f08dc1-6b7e-4d92-9251-4fcd1340b025
//******
//fe6943a9-7308-47be-88d3-bede2bcf32e9
//******
//ac8d977d-4f6b-49b8-bc7e-74c3ef943ab4
//******
//f128b718-6963-40fe-b1dd-5540af76c894
//******
//4a66bd9d-7af9-4575-993e-485de8409a7a
//******