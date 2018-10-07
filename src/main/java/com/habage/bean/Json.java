package com.habage.bean;

import com.alibaba.fastjson.JSON;

public class Json {
    private Integer id;
    private  String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
