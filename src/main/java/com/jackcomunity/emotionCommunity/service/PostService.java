package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.PostCreate;
import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
