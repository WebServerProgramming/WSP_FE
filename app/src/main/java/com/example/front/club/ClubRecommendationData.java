package com.example.front.club;

public class ClubRecommendationData {

    private String content;
    public Boolean isQuery;

    public ClubRecommendationData(String content, Boolean isQuery) {
        this.content = content;
        this.isQuery = isQuery;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
