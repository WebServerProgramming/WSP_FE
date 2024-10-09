package com.example.front.main;

public class Main3Data {

    private int ivRoomListClub;
    private String tvRoomListName;
    private String tvRoomListIntroduction;

    public Main3Data(int ivRoomListClub, String tvRoomListName, String tvRoomListIntroduction) {
        this.ivRoomListClub = ivRoomListClub;
        this.tvRoomListName = tvRoomListName;
        this.tvRoomListIntroduction = tvRoomListIntroduction;
    }

    public int getIvRoomListClub() {
        return ivRoomListClub;
    }

    public void setIvRoomListClub(int ivRoomListClub) {
        this.ivRoomListClub = ivRoomListClub;
    }

    public String getTvRoomListName() {
        return tvRoomListName;
    }

    public void setTvRoomListName(String tvRoomListName) {
        this.tvRoomListName = tvRoomListName;
    }

    public String getTvRoomListIntroduction() {
        return tvRoomListIntroduction;
    }

    public void setTvRoomListIntroduction(String tvRoomListIntroduction) {
        this.tvRoomListIntroduction = tvRoomListIntroduction;
    }

}
