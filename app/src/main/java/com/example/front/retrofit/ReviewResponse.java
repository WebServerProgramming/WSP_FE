package com.example.front.retrofit;

import java.util.List;

public class ReviewResponse {
    private Result result;
    private Payload payload;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public static class Payload {
        private Double totalRate;
        private List<Reviews> reviews;

        public Double getTotalRate() {
            return totalRate;
        }

        public void setTotalRate(Double totalRate) {
            this.totalRate = totalRate;
        }

        public List<Reviews> getReviews() {
            return reviews;
        }

        public void setReviews(List<Reviews> reviews) {
            this.reviews = reviews;
        }
    }

    public static class Reviews {
        private String content;
        private int rate;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }
    }

}
