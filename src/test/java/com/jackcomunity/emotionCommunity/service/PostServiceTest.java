package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.request.PostCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @BeforeEach
    public void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성 테스트")
    public void test(){
        PostCreate post = PostCreate.builder()
                .title("title1")
                .content("content1")
                .build();
        postService.write(post);

        Assertions.assertEquals(1L, postRepository.count());
    }
}