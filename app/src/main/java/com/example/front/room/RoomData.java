package com.example.front.room;

public class RoomData {

    private String tvRoomCategory;
    private String tvContent;
    private int id;

    public String getTvRoomCategory() {
        return tvRoomCategory;
    }

    public void setTvRoomCategory(String tvRoomCategory) {
        this.tvRoomCategory = tvRoomCategory;
    }

    public String getTvContent() {
        return tvContent;
    }

    public void setTvContent(String tvContent) {
        this.tvContent = tvContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomData(String tvRoomCategory, String tvContent, int id) {
        this.tvRoomCategory = tvRoomCategory;
        this.tvContent = tvContent;
        this.id = id;
    }
}
