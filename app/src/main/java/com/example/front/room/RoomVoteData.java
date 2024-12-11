package com.example.front.room;

public class RoomVoteData {

    private String tvVoteContent;
    private int voteId;

    public RoomVoteData(String tvVoteContent, int voteId) {
        this.tvVoteContent = tvVoteContent;
        this.voteId = voteId;
    }

    public String getTvVoteContent() {
        return tvVoteContent;
    }

    public void setTvVoteContent(String tvVoteContent) {
        this.tvVoteContent = tvVoteContent;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }
}
