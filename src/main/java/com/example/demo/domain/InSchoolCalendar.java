package com.example.demo.domain;

import java.util.List;

public class InSchoolCalendar {
    List<Schedule> scheduleList;
    Integer schoolCalendarId;

    public InSchoolCalendar(List<Schedule> scheduleList, Integer schoolCalendarId) {
        this.scheduleList = scheduleList;
        this.schoolCalendarId = schoolCalendarId;
    }

    public InSchoolCalendar() {

    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public void setSchoolCalendarId(Integer schoolCalendarId) {
        this.schoolCalendarId = schoolCalendarId;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public Integer getSchoolCalendarId() {
        return schoolCalendarId;
    }
}
