package com.example.demo.domain;

import javax.persistence.*;

@Entity
public class LoginUser {

    String userId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer loginUserId;

    public Integer getLoginUserId() {
        return loginUserId;
    }

    public LoginUser(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public LoginUser() {
    }
}
