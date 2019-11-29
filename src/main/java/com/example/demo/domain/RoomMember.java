
package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer memberId;

    String userId;
    Integer roomId;
    int memberRight;

    public Integer getMemberId() {
        return memberId;
    }

    public RoomMember(String userId, Integer roomId, int memberRight) {
        this.userId = userId;
        this.roomId = roomId;
        this.memberRight = memberRight;
    }

    public int getMemberRight() {
        return memberRight;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setMemberRight(int memberRight) {
        this.memberRight = memberRight;
    }

    public RoomMember() {
    }
}