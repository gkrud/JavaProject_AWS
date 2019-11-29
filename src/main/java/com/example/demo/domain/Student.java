package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

        @Id
        String id;
        String pw;

        int classOf;
        int iconIndex;
        Integer myCalendarId;
        Boolean isAdmin;
    public Integer getMyCalendarId() {
        return myCalendarId;
    }

    public void setMyCalendarId(Integer myCalendarId) {
        this.myCalendarId = myCalendarId;
    }

    public void setIconIndex(int iconIndex) {
        this.iconIndex = iconIndex;
    }

    public int getIconIndex() {
        return iconIndex;
    }

    public int getClassOf() {
        return classOf;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClassOf(int classOf) {
        this.classOf = classOf;
    }

    public String getPw(){return this.pw;}
    public String getId(){return this.id;}

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setStudent(Student student) {
        this.id = student.getId();
        this.pw = student.getPw();
        this.classOf = student.getClassOf();
        this.iconIndex = student.getIconIndex();
    }
}
