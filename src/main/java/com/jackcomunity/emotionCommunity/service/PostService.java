package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.exception.PostNotFound;
import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.PostCreate;
import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import com.jackcomunity.emotionCommunity.util.Emotion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.jackcomunity.emotionCommunity.util.Emotion.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Transactional
    public void write(PostCreate postCreate, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        postCreate.setUser(user);
        Post writePost = postCreate.toEntity();

        postRepository.save(writePost);
    }

    public Page<PostResponse> getList(Pageable pageable) {
        return postRepository.findAll(pageable).map(PostResponse::new);
    }

    public Page<PostResponse> search(String searchText, Pageable pageable) {
        return postRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable).map(PostResponse::new);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);
        return new PostResponse(post);
    }

    public PostResponse getWithEmotion(Long id, Emotion emotion) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);
        PostResponse postResponse = new PostResponse(post);
        if (emotion.equals(POSITIVE)) {
            postResponse.getCommentResponses().removeIf(comment -> !comment.getEmotion().equals(POSITIVE));

        }
        if (emotion.equals(NEUTRAL)) {
            postResponse.getCommentResponses().removeIf(comment -> comment.getEmotion().equals(NEGATIVE));
        }
        return postResponse;
    }

    @Transactional
    public void edit(Long postId, PostEdit postEdit) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);
        post.edit(postEdit);
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);
        postRepository.delete(post);
    }
}
