package com.example.front.club;

public class ClubRecommendationData {

    private String content;
    public boolean isQuery;

    public ClubRecommendationData(String content, boolean isQuery) {
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
