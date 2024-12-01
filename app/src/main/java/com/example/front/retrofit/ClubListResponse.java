package com.example.front.retrofit;

import java.util.List;

public class ClubListResponse {
    private Result result;
    private List<Club> payload;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Club> getPayload() {
        return payload;
    }

    public void setPayload(List<Club> payload) {
        this.payload = payload;
    }

    public static class Result {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class Club {
        private int id;
        private String name;
        private Double rate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }
    }
}
