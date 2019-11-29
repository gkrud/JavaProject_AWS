package com.example.demo.domain;

import java.util.List;

public class InRoom {

    List<Schedule> schedules;
    Room room;

    public InRoom(List<Schedule> schedules,Room room) {
        this.schedules = schedules;
        this.room = room;
    }

    public InRoom() {
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Room getRoom() {
        return room;
    }

}
