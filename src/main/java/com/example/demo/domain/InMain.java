package com.example.demo.domain;

import java.sql.Time;
import java.util.List;

public class InMain {
    List<Event> eventList;
    List<Notice> notices;
    List<TimeTable> timeTables;
    List<Schedule> schedules;

    public InMain(List<Event> eventList, List<Notice> notices, List<TimeTable> timeTables, List<Schedule> schedules) {
        this.eventList = eventList;
        this.notices = notices;
        this.timeTables = timeTables;
        this.schedules = schedules;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public void setTimeTables(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }

    public InMain() {
    }

    @Override
    public String toString() {
        return "InMain{" +
                "eventList=" + eventList +
                ", notices=" + notices +
                ", timeTables=" + timeTables +
                '}';
    }
}
