package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.PostCreate;
import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void write(PostCreate postCreate, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        Post writePost = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        user.addPost(writePost);// 컨비니언스 메소드
        postRepository.save(writePost);
    }

    public List<PostResponse> getList() {
        return postRepository.findAll().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return PostResponse.builder()
                .post(post)
                .build();
    }

    @Transactional
    public void edit(Long postId, PostEdit postEdit) {
        Post post = postRepository.findById(postId)
                .orElseThrow();
        post.edit(postEdit);
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
