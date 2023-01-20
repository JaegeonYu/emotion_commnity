package com.jackcomunity.emotionCommunity.request;

import lombok.Builder;
import lombok.Data;

@Data
public class UserCheck {
    private String username;
    private String nickname;
    private String email;
    private String objectName;
    public UserCheck(UserCreate userCreate) {
        this.username = userCreate.getUsername();
        this.nickname = userCreate.getNickname();
        this.email = userCreate.getEmail();
        this.objectName = "userCreate";
    }

    public UserCheck(UserEdit userEdit){
        this.username = userEdit.getUsername();
        this.nickname = userEdit.getNickname();
        this.email = userEdit.getEmail();
        this.objectName = "userEdit";
    }
}
