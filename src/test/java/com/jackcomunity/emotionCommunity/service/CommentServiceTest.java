package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.entity.Comment;
import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.repository.CommentRepository;
import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.CommentCreate;
import com.jackcomunity.emotionCommunity.request.CommentEdit;
import com.jackcomunity.emotionCommunity.util.Emotion;
import com.jackcomunity.emotionCommunity.util.Roles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class CommentServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    private User user;
    private Post post;
    private Comment comment;

    @Test
    public void save_test(){
        saveUserToRepository();
        savePostToRepository();
        CommentCreate commentTest = CommentCreate.builder().content("comment test").build();
        commentService.save(commentTest, user.getUsername(), 1L);

        Assertions.assertEquals(commentTest.getContent(),commentRepository.findAll().get(0).getContent());
    }

    @Test
    public void edit_test(){
        saveUserToRepository();
        savePostToRepository();
        saveCommentToRepository();
        CommentEdit commentEdit = CommentEdit.builder().content("modify").build();
        commentService.edit(commentEdit, comment.getId());

        Comment findComment = commentRepository.findById(comment.getId()).get();
        Assertions.assertEquals(commentEdit.getContent(), findComment.getContent());
    }

    private void savePostToRepository() {
        post = postRepository.save(Post.builder().title("title").content("content").user(user).build());
    }

    private void saveUserToRepository() {
        user = userRepository.save(User.builder().username("jack").password(passwordEncoder.encode("1234"))
                .role(Roles.USER)
                .email("yjk98053@gmail.com").nickname("biobebe").build());
    }

    private void saveCommentToRepository(){
        comment = commentRepository.save(Comment.builder().content("test").emotion(Emotion.NEUTRAL).post(post).user(user).build());
    }

}