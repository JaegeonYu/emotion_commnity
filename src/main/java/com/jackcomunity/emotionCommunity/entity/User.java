package com.jackcomunity.emotionCommunity.entity;

import com.jackcomunity.emotionCommunity.util.Emotion;
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
public class User extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;
    @Enumerated(EnumType.STRING)
    private Emotion emotion;
    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public User(String username, String nickname, String email, String password, Roles role, Emotion emotion) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.emotion = emotion;

    }
    public void edit(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }
    public void edit(Emotion emotion){
        this.emotion = emotion;
    }

    public void addComment(Comment comment) {
        comment.setUser(this);
        comments.add(comment);
    }
}
