package okhttp;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.ContactResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class AddNewContactTestsOkhttp {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWljaGFlbCsxQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc3ODI2MzMzLCJpYXQiOjE2NzcyMjYzMzN9.GmGnBolwIbF69fr515hKVmO5Ar8lYe3vrgZg5Qk9FvY";
    private final MediaType JSON = MediaType.get("application/json;charset=utf-8");

    @Test
    public void addNewContactSuccess() throws IOException {
        int i = new Random().nextInt(1000)+1000;
        ContactDto dto = ContactDto.builder()
                .name("Lisa")
                .lastName("Brown")
                .email("lisa"+i+"@mail.ru")
                .phone("7896541235")
                .address("NY")
                .description("Daughter").build();

        RequestBody body = RequestBody.create(gson.toJson(dto),JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization",token)
                .post(body).build();
        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);
        ContactResponseDto resdto =  gson.fromJson(response.body().string(), ContactResponseDto.class);
        System.out.println(resdto.getMessage());
        Assert.assertTrue(resdto.getMessage().contains("Contact was added!"));
    }

    @Test
    public void addNewContactWrongName() throws IOException {
        ContactDto dto = ContactDto.builder()
                .lastName("Brown")
                .email("lisa@mail.ru")
                .phone("7896541235")
                .address("NY")
                .description("Daughter").build();

        RequestBody body = RequestBody.create(gson.toJson(dto),JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization",token)
                .post(body).build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),400);

        ErrorDto errorDto = gson.fromJson(response.body().string(),ErrorDto.class);
        System.out.println(errorDto.getMessage());
        Assert.assertEquals(errorDto.getMessage(),"{name=must not be blank");
    }
}
