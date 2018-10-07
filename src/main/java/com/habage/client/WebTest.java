package com.habage.client;

import com.alibaba.fastjson.JSON;
import com.habage.bean.Student;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class WebTest {
    public static void main(String[] args) throws UnirestException {

        Unirest.setObjectMapper(new ObjectMapper() {
            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                return JSON.parseObject(value, valueType);
            }

            @Override
            public String writeValue(Object value) {
                return JSON.toJSONString(value);
            }
        });

        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://appapi.heclouds.com/onenetapp/v3")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .asJson();

        JSONObject v3 = jsonResponse.getBody().getObject();
        System.out.println(v3);

        Student student = Unirest.get("http://appapi.heclouds.com/onenetapp/v3")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .asObject(Student.class).getBody();
        System.out.println(student);
    }
}
