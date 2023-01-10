package com.jackcomunity.emotionCommunity.response;

import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String username;
    private LocalDate createDate;
    private List<CommentResponse> commentResponses;
    @Builder
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUser().getId();
        this.username = post.getUser().getUsername();
        this.createDate = post.getCreatedDate();
        this.commentResponses = post.getComments().stream().map(CommentResponse::new).collect(Collectors.toList());
    }
}
