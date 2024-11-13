package com.example.front.main;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class MainScheduleData {

    private int ivCalendarList;
    private String tvCalendarList;
    private CalendarDay calendarDay;

    public MainScheduleData(int ivCalendarList, String tvCalendarList, CalendarDay calendarDay) {
        this.ivCalendarList = ivCalendarList;
        this.tvCalendarList = tvCalendarList;
        this.calendarDay = calendarDay;
    }

    public int getIvCalendarList() {
        return ivCalendarList;
    }

    public void setIvCalendarList(int ivCalendarList) {
        this.ivCalendarList = ivCalendarList;
    }

    public String getTvCalendarList() {
        return tvCalendarList;
    }

    public void setTvCalendarList(String tvCalendarList) {
        this.tvCalendarList = tvCalendarList;
    }

    public CalendarDay getCalendarDay() {
        return calendarDay;
    }

    public void setCalendarDay(CalendarDay calendarDay) {
        this.calendarDay = calendarDay;
    }
}
