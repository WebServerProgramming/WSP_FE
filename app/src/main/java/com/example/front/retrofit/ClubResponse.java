package com.example.front.retrofit;

import java.util.List;

public class ClubResponse {
    private Result result;  // private으로 설정하고 getter 추가
    private List<Club> payload;

    // Getter & Setter 추가
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

    // 내부 클래스도 public으로 설정
    public static class Result {
        private int code;
        private String message;

        // Getter & Setter 추가
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

        // Getter & Setter 추가
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
    }
}
