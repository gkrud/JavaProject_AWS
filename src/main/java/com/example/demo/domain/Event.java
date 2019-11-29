package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer eventId;
    String eventPoster;
    String eventDetail;
    String startDate;
    String endDate;
    Boolean eventStatus;

    public void setEventStatus(Boolean eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }


    public void setEventPoster(String eventPoster) {
        this.eventPoster = eventPoster;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getEventId() {
        return eventId;
    }

    public String getEventPoster() {
        return eventPoster;
    }

    public String getEventDetail() {
        return eventDetail;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getEventStatus() {
        return eventStatus;
    }

    public Event() {
    }

    public Event(String eventDetail, String startDate, String endDate) {
        this.eventDetail = eventDetail;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
