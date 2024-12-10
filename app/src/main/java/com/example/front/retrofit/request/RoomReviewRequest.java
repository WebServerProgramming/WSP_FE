package com.example.front.retrofit.request;

public class RoomReviewRequest {
    public int rating;
    public String content;

    public RoomReviewRequest(int rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
