package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.Data;

@Data
public class UserEdit {
    private String username;
    private String nickname;
    private String password;

    private String email;
}
