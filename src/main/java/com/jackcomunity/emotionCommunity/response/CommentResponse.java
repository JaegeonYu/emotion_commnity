package com.jackcomunity.emotionCommunity.response;

import com.jackcomunity.emotionCommunity.entity.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {
    private Long id;
    private String content;
    private Long userId;

    @Builder
    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUser().getId();
    }
}
