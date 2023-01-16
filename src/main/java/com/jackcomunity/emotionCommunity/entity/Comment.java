package com.jackcomunity.emotionCommunity.entity;

import com.jackcomunity.emotionCommunity.request.CommentCreate;
import com.jackcomunity.emotionCommunity.request.CommentEdit;
import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @Enumerated(EnumType.STRING)
    private Emotion emotion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Setter
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @Builder
    public Comment(String content, Emotion emotion, Post post, User user) {
        this.content = content;
        this.emotion = emotion;
        this.post = post;
        this.user = user;
    }

    public void edit(CommentEdit commentEdit){
        this.content = commentEdit.getContent();
        this.emotion = commentEdit.getEmotion();
    }
}
