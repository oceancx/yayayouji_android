package com.yayayouji.net;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by oceancx on 15/10/24.
 */
public class YYHttpClient {

    public static final MediaType JSON
            = MediaType.parse("text/html; charset=utf-8");
    public static OkHttpClient client = new OkHttpClient();

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("name", "3123");
        builder.add("captain", "true");
        builder.add("gender", "F");
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}

