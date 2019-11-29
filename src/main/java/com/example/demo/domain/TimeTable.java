package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class TimeTable {
    @Id
    Integer timeTableIndex;
    String teacher;

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher() {
        return teacher;
    }

    String subject;

    public Integer getTimeTableIndex() {
        return timeTableIndex;
    }

    public void setTimeTableIndex(Integer timeTableIndex) {
        this.timeTableIndex = timeTableIndex;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
