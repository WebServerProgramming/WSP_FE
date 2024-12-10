package com.example.front.retrofit;

public class SingleStringResponse {
    Result result;
    String payload;

    public SingleStringResponse(Result result, String payload) {
        this.result = result;
        this.payload = payload;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
