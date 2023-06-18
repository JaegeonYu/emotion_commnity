package com.jackcomunity.emotionCommunity.entity;

import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Post extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    @Builder
    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void edit(PostEdit postEdit) {
        this.title = postEdit.getTitle();
        this.content = postEdit.getContent();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}

