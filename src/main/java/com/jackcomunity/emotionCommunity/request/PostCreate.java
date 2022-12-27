package com.jackcomunity.emotionCommunity.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreate {
    private String title;
    private String content;
    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
