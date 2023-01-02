package com.jackcomunity.emotionCommunity.request;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
@Getter
public class UserCreate {

    private String username;
    private String password;

    @Builder
    public UserCreate(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
