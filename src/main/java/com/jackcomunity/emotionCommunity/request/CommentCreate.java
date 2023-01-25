package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.entity.Comment;
import com.jackcomunity.emotionCommunity.util.Emotion;
import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CommentCreate {
    @NotBlank

    private String content;
    private Emotion emotion;
    private Post post;
    private User user;

    @Builder
    public CommentCreate(String content) {
        this.content = content;
    }

    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .emotion(emotion)
                .post(post)
                .user(user).build();
    }
}
