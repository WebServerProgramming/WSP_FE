package com.example.front.club;

public class ClubListData {

    private int ivRoomListClub;
    private String tvRoomListName;
    private String tvRoomListIntroduction;
    private String tvRoomListStar;
    private int id;

    public ClubListData(int ivRoomListClub, String tvRoomListName, String tvRoomListIntroduction, String tvRoomListStar, int id) {
        this.ivRoomListClub = ivRoomListClub;
        this.tvRoomListName = tvRoomListName;
        this.tvRoomListIntroduction = tvRoomListIntroduction;
        this.tvRoomListStar = tvRoomListStar;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
