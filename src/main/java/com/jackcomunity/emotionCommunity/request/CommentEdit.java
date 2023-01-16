package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.entity.Comment;
import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.Builder;
import lombok.Data;
@Data
public class CommentEdit {
    private String content;
    private Emotion emotion;

    @Builder
    public CommentEdit(String content) {
        this.content = content;
    }

}
