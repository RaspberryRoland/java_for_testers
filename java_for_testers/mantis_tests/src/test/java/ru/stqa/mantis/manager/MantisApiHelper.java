package ru.stqa.mantis.manager;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class MantisApiHelper extends HelperBase{

    public static final MediaType JSON = MediaType.get("application/json");

    OkHttpClient client;

    public MantisApiHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void registerNewUser(String user, String email){
        RequestBody body = RequestBody.create(String.format("{\"username\":\"%s\",\"email\":\"%s\"}", user, email), JSON);
        System.out.println("This is my body " + body);
        Request request = new Request.Builder()
                .url(String.format("%s/api/rest/users", manager.property("web.baseUrl")))
                .post(body)
                .header("Authorization", manager.property("apiKey"))
                .build();
        System.out.println("This is my request " + request);
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
