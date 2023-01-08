package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
public class PostCreate {
    @NotBlank
    private String title;
    private String content;
    private User user;


    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public Post toEntity(){
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .user(this.user)
                .build();
    }
}
