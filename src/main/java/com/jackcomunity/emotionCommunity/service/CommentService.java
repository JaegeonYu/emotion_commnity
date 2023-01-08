package com.jackcomunity.emotionCommunity.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jackcomunity.emotionCommunity.entity.Comment;
import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
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

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TemplateCreate templateCreate;


    public void save(CommentCreate commentCreate, String username, Long postId) throws JsonProcessingException {
        Emotion emotion = emotionDiscrimination(commentCreate);
        commentCreate.setEmotion(emotion);

        Comment comment = commentCreate.toEntity();

        User user = userRepository.findByUsername(username).orElseThrow();
        user.addComment(comment);
        Post post = postRepository.findById(postId).orElseThrow();
        post.addComment(comment);

        commentRepository.save(comment);
    };
    private Emotion emotionDiscrimination(CommentCreate commentCreate) throws JsonProcessingException {
        EmotionDiscrimination emotionDiscrimination = new EmotionDiscrimination(commentCreate.getContent());
        Json template = templateCreate.createTemplate(emotionDiscrimination);
        System.out.println("loglog"+template.toString());
        return Emotion.of(template.toString());
    }
}
