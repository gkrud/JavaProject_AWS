package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer roomId;

    public void setCalendarId(Integer calendarId) {
        this.calendarId = calendarId;
    }

    int iconIndex;
    Integer calendarId;
    String roomTitle;
    public String getRoomTitle() {
        return roomTitle;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public int getIconIndex() {
        return iconIndex;
    }

    public Integer getCalendarId() {
        return calendarId;
    }

    public Room(int iconIndex, Integer calendarId, String roomTitle) {
        this.iconIndex = iconIndex;
        this.calendarId = calendarId;
        this.roomTitle = roomTitle;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setIconIndex(int iconIndex) {
        this.iconIndex = iconIndex;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public Room() {
    }
}
