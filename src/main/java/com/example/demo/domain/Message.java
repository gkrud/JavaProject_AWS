package com.example.demo.domain;

import org.hibernate.exception.DataException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer messageId;

    Boolean isHaveDialog;
    String toUserId;
    String sendDateNow;
    Integer roomId;
    Integer eventId;
    String sendUserId;
    int type;

    public Message(Boolean isHaveDialog, String toUserId, String sendDateNow, Integer roomId, Integer eventId, int type, String sendUserId) {
        this.isHaveDialog = isHaveDialog;
        this.toUserId = toUserId;
        this.sendDateNow = sendDateNow;
        this.roomId = roomId;
        this.eventId = eventId;
        this.type = type;
        this.sendUserId = sendUserId;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Boolean getHaveDialog() {
        return isHaveDialog;
    }

    public String getSendDateNow() {
        return sendDateNow;
    }

    public Integer getEventId() {
        return eventId;
    }

    public int getType() {
        return type;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public Message() {
    }
}
