package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer noticeId;

    String noticeContent;
    String startDate;
    String endDate;
    String noticeTitle;
    public Integer getNoticeId() {
        return noticeId;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNotice(String noticeContent, String startDate, String endDate, String noticeTitle){
        this.noticeContent = noticeContent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.noticeTitle = noticeTitle;
    }

}
