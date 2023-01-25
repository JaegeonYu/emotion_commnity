package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmotionEdit {
    private Emotion emotion;
    @NotBlank(message = "비밀번호 필수 입력값입니다.")
    private String password;
    private String username;
}
