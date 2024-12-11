package com.example.front.retrofit;

import java.util.List;

public class CalendarResponse {
    Result result;
    List<String> payload;

    public CalendarResponse(Result result, List<String> payload) {
        this.result = result;
        this.payload = payload;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<String> getPayload() {
        return payload;
    }

    public void setPayload(List<String> payload) {
        this.payload = payload;
    }
}
