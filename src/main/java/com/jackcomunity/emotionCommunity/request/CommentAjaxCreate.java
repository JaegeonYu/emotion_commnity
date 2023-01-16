package com.jackcomunity.emotionCommunity.request;

import com.jackcomunity.emotionCommunity.entity.Comment;
import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentAjaxCreate {
    private String content;
    private String username;
    private Long postId;
    private Emotion emotion;
    private Post post;
    private User user;
    @Builder
    public CommentAjaxCreate(String content, String username, Long postId) {
        this.postId = postId;
        this.content = content;
        this.username = username;
    }

    public Comment toEntity(){
        return Comment.builder()
                .content(this.content)
                .emotion(this.emotion)
                .post(this.post)
                .user(this.user).build();
    }
}
