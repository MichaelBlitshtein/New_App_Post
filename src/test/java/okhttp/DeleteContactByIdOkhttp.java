package okhttp;

import com.google.gson.Gson;
import dto.ContactResponseDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIdOkhttp {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWljaGFlbCsxQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc3ODI2MzMzLCJpYXQiOjE2NzcyMjYzMzN9.GmGnBolwIbF69fr515hKVmO5Ar8lYe3vrgZg5Qk9FvY";

    @Test
    public void deleteContactByIdSuccess() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/{id}")
                .delete()
                .addHeader("Authorization",token).build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        gson.fromJson(response.body().string(), ContactResponseDto.class);
        Assert.assertEquals(resdto.getMessage);
    }
}
