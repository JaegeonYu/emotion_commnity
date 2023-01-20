package com.jackcomunity.emotionCommunity.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jackcomunity.emotionCommunity.entity.Comment;
import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.request.CommentAjaxCreate;
import com.jackcomunity.emotionCommunity.request.CommentEdit;
import com.jackcomunity.emotionCommunity.response.CommentResponse;
import com.jackcomunity.emotionCommunity.util.Emotion;
import com.jackcomunity.emotionCommunity.repository.CommentRepository;
import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.CommentCreate;
import com.jackcomunity.emotionCommunity.util.api.EmotionDiscrimination;
import com.jackcomunity.emotionCommunity.util.api.Json;
import com.jackcomunity.emotionCommunity.util.api.TemplateCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TemplateCreate templateCreate;


    public void save(CommentCreate commentCreate, String username, Long postId){
        Emotion emotion = emotionDiscrimination(commentCreate.getContent());
        commentCreate.setEmotion(emotion);

        Comment comment = commentCreate.toEntity();

        User user = userRepository.findByUsername(username).orElseThrow();
        user.addComment(comment);
        Post post = postRepository.findById(postId).orElseThrow();
        post.addComment(comment);

        commentRepository.save(comment);
    };

//    public Long ajaxCreate(CommentAjaxCreate commentCreate, Long postId){
//        Emotion emotion = emotionDiscrimination(commentCreate.getContent());
//        commentCreate.setEmotion(emotion);
//        User user = userRepository.findByUsername(commentCreate.getUsername()).orElseThrow();
//        Post post = postRepository.findById(postId).orElseThrow();
//        commentCreate.setUser(user);
//        commentCreate.setPost(post);
//
//        Comment comment = commentCreate.toEntity();
//        commentRepository.save(comment);
//
//        return comment.getId();
//    }

    public List<CommentResponse> getList(Long postId){
        return postRepository.findById(postId).orElseThrow().getComments().stream().map(CommentResponse::new).collect(Collectors.toList());
    }
    @Transactional
    public void edit(CommentEdit commentEdit, Long commentID)  {
        Emotion emotion = emotionDiscrimination(commentEdit.getContent());
        commentEdit.setEmotion(emotion);
        Comment comment = commentRepository.findById(commentID).orElseThrow();
        comment.edit(commentEdit);

    }
    private Emotion emotionDiscrimination(String content)  {
        EmotionDiscrimination emotionDiscrimination = new EmotionDiscrimination(content);
        Json template;
        try {
            template = templateCreate.createTemplate(emotionDiscrimination);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Emotion.of(template.toString());
    }
    @Transactional
    public void delete(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        commentRepository.delete(comment);
    }
}
