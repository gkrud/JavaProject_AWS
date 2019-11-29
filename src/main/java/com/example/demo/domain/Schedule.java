package com.example.demo.domain;



import org.springframework.web.bind.annotation.GetMapping;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Calendar;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer scheduleId;

    String scheduleTitle;
    @Column(name = "calendar_id")
    Integer calendarId;
    String startDate;
    String endDate;
    String scheduleContent;
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setSchedule(String scheduleTitle, Integer calendarId, String startDate, String endDate, String scheduleContent) {
        this.scheduleTitle = scheduleTitle;
        this.calendarId = calendarId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scheduleContent = scheduleContent;
    }

    public Schedule() {
    }

    public Schedule(String scheduleTitle, Integer calendarId, String startDate, String endDate, String scheduleContent) {
        this.scheduleTitle = scheduleTitle;
        this.calendarId = calendarId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scheduleContent = scheduleContent;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public String getScheduleContent() {
        return scheduleContent;
    }

    public Integer getCalendarId() {
        return calendarId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    public String getEndDate() {
        return endDate;
    }
}
