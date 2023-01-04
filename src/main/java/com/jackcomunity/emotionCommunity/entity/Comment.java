package com.jackcomunity.emotionCommunity.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
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

}
