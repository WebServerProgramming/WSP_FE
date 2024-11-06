package com.example.front.main;

public class Main3Data {

    private int ivRoomListClub;
    private String tvRoomListName;
    private String tvRoomListIntroduction;
    private String tvRoomListStar;

    public Main3Data(int ivRoomListClub, String tvRoomListName, String tvRoomListIntroduction, String tvRoomListStar) {
        this.ivRoomListClub = ivRoomListClub;
        this.tvRoomListName = tvRoomListName;
        this.tvRoomListIntroduction = tvRoomListIntroduction;
        this.tvRoomListStar = tvRoomListStar;
    }

    public int getIvRoomListClub() {
        return ivRoomListClub;
    }

    public void setIvRoomListClub(int ivRoomListClub) {
        this.ivRoomListClub = ivRoomListClub;
    }

    public String getTvRoomListIntroduction() {
        return tvRoomListIntroduction;
    }

    public void setTvRoomListIntroduction(String tvRoomListIntroduction) {
        this.tvRoomListIntroduction = tvRoomListIntroduction;
    }

    public String getTvRoomListName() {
        return tvRoomListName;
    }

    public void setTvRoomListName(String tvRoomListName) {
        this.tvRoomListName = tvRoomListName;
    }

    public String getTvRoomListStar() {
        return tvRoomListStar;
    }

    public void setTvRoomListStar(String tvRoomListStar) {
        this.tvRoomListStar = tvRoomListStar;
    }
}
