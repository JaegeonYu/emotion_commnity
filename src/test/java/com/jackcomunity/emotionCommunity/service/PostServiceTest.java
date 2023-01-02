package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.entity.Post;
import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.request.PostCreate;
import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @BeforeEach
    public void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성 테스트")
    public void writeTest() {
        PostCreate post = PostCreate.builder()
                .title("title1")
                .content("content1")
                .build();
        postService.write(post, "eel");

        Assertions.assertEquals(1L, postRepository.count());
    }

    @Test
    @DisplayName("글 조회 테스트")
    public void getTest() {
        Post post = Post.builder()
                .title("title1")
                .content("content1")
                .build();
        postRepository.save(post);

        PostResponse postResponse = postService.get(post.getId());
        Assertions.assertEquals(post.getTitle(), postResponse.getTitle());
        Assertions.assertEquals(post.getContent(), postResponse.getContent());
    }

    @Test
    @DisplayName("게시글 여러개 조회 테스트")
    public void getListTest() throws Exception {
        //given
        List<Post> posts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("title " + i)
                        .content("content " + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(posts);
        //when

        List<PostResponse> responses = postService.getList();

        //then
        assertEquals(responses.size(), 20L);
    }

    @Test
    @DisplayName("글 제목 수정 테스트")
    public void editTest() {
        //given
        Post post = Post.builder()
                .title("title")
                .content("content")
                .build();
        postRepository.save(post);
        //when
        PostEdit postEdit = PostEdit.builder()
                .title("changeTitle")
                .content(post.getContent())
                .build();
        postService.edit(post.getId(), postEdit);
        //then
        Post changedPost = postRepository.findById(post.getId()).orElseThrow();
        Assertions.assertEquals(changedPost.getTitle(), postEdit.getTitle());
    }
}