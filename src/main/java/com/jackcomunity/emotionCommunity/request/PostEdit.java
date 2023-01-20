package com.jackcomunity.emotionCommunity.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostEdit {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Builder
    public PostEdit(String title, String content) {

        this.title = title;
        this.content = content;
    }


}
