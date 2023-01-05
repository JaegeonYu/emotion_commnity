package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.entity.Comment;
import com.jackcomunity.emotionCommunity.entity.Emotion;
import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentCreate {

    private String content;
    private Emotion emotion;
    private Post post;
    private User user;

    @Builder
    public CommentCreate(String content, Emotion emotion, Post post, User user) {
        this.content = content;
        this.emotion = emotion;
        this.post = post;
        this.user = user;
    }

    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .emotion(emotion)
                .post(post)
                .user(user).build();
    }
}
