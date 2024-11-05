package com.example.front.room;

public class RoomData {

    private String tvRoomCategory;
    private String tvContent;

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

    public RoomData(String tvRoomCategory, String tvContent) {
        this.tvRoomCategory = tvRoomCategory;
        this.tvContent = tvContent;


    }
}
