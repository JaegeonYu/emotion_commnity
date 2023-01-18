package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.Data;

@Data
public class EmotionEdit {
    private Emotion emotion;
    private String password;
    private String username;
}
