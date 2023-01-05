package com.jackcomunity.emotionCommunity.response;

import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;

    private List<CommentResponse> commentResponses;
    @Builder
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUser().getId();
        this.commentResponses = post.getComments().stream().map(CommentResponse::new).collect(Collectors.toList());
    }
}
