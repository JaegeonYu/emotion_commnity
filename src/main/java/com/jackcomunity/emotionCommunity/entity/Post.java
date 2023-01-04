package com.jackcomunity.emotionCommunity.entity;

import com.jackcomunity.emotionCommunity.request.PostEdit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void edit(PostEdit postEdit) {
        this.title = postEdit.getTitle();
        this.content = postEdit.getContent();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }
}

