package com.fjapi.flickjunkies.fjcore.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

final public class ApiClient
{
    public static JSONObject queryApi(String query) throws IOException
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(query).build();
        Response response = client.newCall(request).execute();
        String jsonData = Objects.requireNonNull(response.body()).string();
        return new JSONObject(jsonData);
    }
}
