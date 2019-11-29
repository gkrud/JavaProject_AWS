package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class InvitedMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer invitedUserId;

    String userId;
    Integer roomId;

    public InvitedMember(String userId, Integer roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public InvitedMember() {
    }
}
