package com.habage.client;

import com.habage.bean.Json;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AkoaService {

    @GET("/json")
    Call<Json> json();

    @GET("/json/{id}")
    Call<List<Json>> jsons(@Path("id") String id, @Query("title") String title);

    @FormUrlEncoded
    @POST("/json")
    Call<Json> jsonPost(@Field("id") Integer id, @Field("title") String title);
}
