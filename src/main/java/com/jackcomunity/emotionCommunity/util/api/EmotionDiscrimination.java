package com.jackcomunity.emotionCommunity.util.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Template;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Getter
public class EmotionDiscrimination {
    private String content;

    public EmotionDiscrimination(String content) {
        this.content = content;
    }
}
