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

    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;


    @Builder
    public Comment(String content, Emotion emotion, User user, Post post) {
        this.content = content;
        this.emotion = emotion;
        this.user = user;
        this.post =post;
    }

    public void edit(CommentEdit commentEdit){
        this.content = commentEdit.getContent();
        this.emotion = commentEdit.getEmotion();
    }
}
