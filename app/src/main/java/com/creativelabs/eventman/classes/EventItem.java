package com.creativelabs.eventman.classes;

import java.util.Date;

public class EventItem {

    private String eventName;
    private String eventDesc;
    private Date startDate;
    private String startTime;

    public EventItem(String eventName, String eventDesc, Date startDate, String startTime) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.startDate = startDate;
        this.startTime = startTime;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
