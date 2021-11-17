package com.creativelabs.eventman.classes;

import java.util.Date;

public class EventItem {

    private String eventName;
    private String eventDesc;
    private String startDate;
    private String startTime;
    private int id;

    public EventItem(String eventName, String eventDesc, String startDate, String startTime) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public EventItem() {

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
