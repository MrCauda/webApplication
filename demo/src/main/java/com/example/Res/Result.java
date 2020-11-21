package com.example.Res;

import java.util.List;
import java.util.Map;

public class Result {
    //响应码
    private int code;

    private List<Map<String, String>> list;

    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Result(List<Map<String, String>> list){
        this.list = list;
    }

}