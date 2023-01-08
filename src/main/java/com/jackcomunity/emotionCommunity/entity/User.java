package com.jackcomunity.emotionCommunity.entity;

import com.jackcomunity.emotionCommunity.util.Roles;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public User(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = role;

    }

    public void addPost(Post post) {
        post.setUser(this);
        posts.add(post);
    }

    public void addComment(Comment comment) {
        comment.setUser(this);
        comments.add(comment);
    }
}
