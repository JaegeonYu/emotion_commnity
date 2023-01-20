package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PostCreate {
    @NotBlank
    private String title;
    @NotBlank
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
