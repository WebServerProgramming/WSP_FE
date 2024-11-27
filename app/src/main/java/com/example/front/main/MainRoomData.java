package com.example.front.main;

public class MainRoomData {
    private int id;
    private int ivClub;
    private String tvClub;

    public MainRoomData(int id, int ivClub, String tvClub) {
        this.id = id;
        this.ivClub = ivClub;
        this.tvClub = tvClub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
