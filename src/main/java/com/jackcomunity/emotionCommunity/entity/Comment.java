package com.jackcomunity.emotionCommunity.entity;

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
    @GeneratedValue
    private Long id;

    private String content;
    @Enumerated(EnumType.STRING)
    private Emotion emotion;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @Setter
    private Post post;
    @ManyToOne
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
}
