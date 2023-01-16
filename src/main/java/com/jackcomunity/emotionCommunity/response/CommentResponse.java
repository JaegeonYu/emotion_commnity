package com.jackcomunity.emotionCommunity.response;

import com.jackcomunity.emotionCommunity.entity.Comment;
import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {
    private Long id;
    private String content;
    private Emotion emotion;
    private String username;
    private String nickname;

    @Builder
    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.emotion = comment.getEmotion();
        this.username = comment.getUser().getUsername();
        this.nickname = comment.getUser().getNickname();
    }
}
