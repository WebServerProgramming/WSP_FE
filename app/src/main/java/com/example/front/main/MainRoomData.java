package com.example.front.main;

public class MainRoomData {

    private int ivClub;
    private String tvClub;

    public MainRoomData(int ivClub, String tvClub) {
        this.ivClub = ivClub;
        this.tvClub = tvClub;
    }

    public int getIvClub() {
        return ivClub;
    }

    public void setIvClub(int ivClub) {
        this.ivClub = ivClub;
    }

    public String getTvClub() {
        return tvClub;
    }

    public void setTvClub(String tvClub) {
        this.tvClub = tvClub;
    }
}
