package com.example.front.club;

public class ClubReviewData {

    private String content;
    private int star;

    public ClubReviewData(String content, int star) {
        this.content = content;
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
