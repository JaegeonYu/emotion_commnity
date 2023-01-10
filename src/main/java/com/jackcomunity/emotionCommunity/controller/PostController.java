package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.request.PostCreate;
import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.CommentResponse;
import com.jackcomunity.emotionCommunity.response.PageDto;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import com.jackcomunity.emotionCommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String list(Model model, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable) {
        Page<PostResponse> posts = postService.getList(pageable);
        PageDto<PostResponse> postPageResponse = PageDto.of(posts);
        model.addAttribute("posts", postPageResponse);

        return "post/posts";
    }

    @GetMapping("/posts/{postId}")
    public String get(@PathVariable Long postId, Model model) {
        PostResponse postResponse = postService.get(postId);

        List<CommentResponse> comments = postResponse.getCommentResponses();
        if (!comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }
        model.addAttribute("post", postResponse);
        return "post/postView";
    }

    @GetMapping("/search")
    public String search(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         String searchText, Model model) {
        Page<PostResponse> searchPosts = postService.search(searchText, pageable);
        PageDto searchPageResponse = PageDto.of(searchPosts);
        model.addAttribute("posts", searchPageResponse);
        model.addAttribute("searchText", searchText);
        return "post/postSearch";
    }

    @GetMapping("/write")
    public String addForm() {
        return "post/postForm";
    }

    @PostMapping("/write")
    public String create(PostCreate postCreate, Authentication authentication, Model model) {
        String username = authentication.getName();
        postService.write(postCreate, username);
        return "redirect:/board/posts";
    }

    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable Long postId, Model model) {
        PostResponse postResponse = postService.get(postId);
        model.addAttribute("editPost", postResponse);
        return "/post/postEdit";
    }

    @PostMapping("/edit/{postId}")
    public String editSave(@PathVariable Long postId, PostEdit postEdit) {
        postService.edit(postId, postEdit);
        return "redirect:/board/posts";
    }

    @GetMapping("/delete/{postId}")
    public String delete(@PathVariable Long postId) {
        postService.delete(postId);
        return "redirect:/board/posts";
    }
}
