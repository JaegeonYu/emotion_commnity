package com.jackcomunity.emotionCommunity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
@Getter
@AllArgsConstructor
public class MessageCreate {
    private String message;
    private String redirectUri;
    private RequestMethod method;
    private Map<String, Object> data;
}
