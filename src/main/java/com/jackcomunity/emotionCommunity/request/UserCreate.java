package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.util.Roles;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;

@Data
public class UserCreate {

    private String username;
    private String email;
    private String nickname;
    private String password;
    private Roles role;


    @Builder
    public UserCreate(String username, String email, String nickname, String password) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .role(this.role).build();
    }
}
