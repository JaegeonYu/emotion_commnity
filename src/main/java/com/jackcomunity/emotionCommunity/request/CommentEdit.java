package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentEdit {
    @NotBlank
    private String content;
    private Emotion emotion;

    @Builder
    public CommentEdit(String content) {
        this.content = content;
    }

}
