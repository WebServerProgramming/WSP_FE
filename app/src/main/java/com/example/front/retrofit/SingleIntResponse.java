package com.example.front.retrofit;

public class SingleIntResponse {
    Result result;
    int payload;

    public SingleIntResponse(Result result, int payload) {
        this.result = result;
        this.payload = payload;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getPayload() {
        return payload;
    }

    public void setPayload(int payload) {
        this.payload = payload;
    }
}
