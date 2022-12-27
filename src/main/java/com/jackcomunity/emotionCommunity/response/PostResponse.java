package com.jackcomunity.emotionCommunity.response;

import com.jackcomunity.emotionCommunity.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    @Builder
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
