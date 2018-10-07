package com.habage.client;

import com.google.gson.Gson;
import com.habage.bean.Json;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class RetrofitTest {
    public static void main(String[] args) throws IOException {
        System.out.println("Retrofit");

        Retrofit retrofit = new Retrofit.Builder()  //01:获取Retrofit对象
                .baseUrl("http://localhost:3000") //02采用链式结构绑定Base url git clone
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();//03执行操作

        AkoaService akoaService = retrofit.create(AkoaService.class);

        Call<Json> call = akoaService.json();
        Response<Json> execute = call.execute();
        Json json = execute.body();
        System.out.println(json);

        Call<List<Json>> helloCall = akoaService.jsons("10", "hello title");
        Response<List<Json>> execute1 = helloCall.execute();
        List<Json> body = execute1.body();
        System.out.println(body);

        Call<Json> jsonCall = akoaService.jsonPost(1, "hell title");
        Response<Json> execute2 = jsonCall.execute();
        Json body1 = execute2.body();
        System.out.println(body1);

        System.out.println("finished");
    }
}
