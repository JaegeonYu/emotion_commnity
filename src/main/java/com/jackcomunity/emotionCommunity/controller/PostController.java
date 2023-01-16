package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.request.PostCreate;
import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.CommentResponse;
import com.jackcomunity.emotionCommunity.response.PageDto;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import com.jackcomunity.emotionCommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String list(Model model, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Page<PostResponse> posts = postService.getList(pageable);
        PageDto<PostResponse> postPageResponse = PageDto.of(posts);
        model.addAttribute("posts", postPageResponse);
        if(userDetails != null){
            model.addAttribute("nickname", userDetails.getNickname());
        }

        return "post/posts";
    }

    @GetMapping("/posts/read/{postId}")
    public String get(@PathVariable Long postId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        PostResponse postResponse = postService.get(postId);

        List<CommentResponse> comments = postResponse.getCommentResponses();
        if (!comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }
        if(userDetails != null){
            model.addAttribute("nickname", userDetails.getNickname());
        }
        model.addAttribute("post", postResponse);
        return "post/postView";
    }

    @GetMapping("/posts/search")
    public String search(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         String searchText, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Page<PostResponse> searchPosts = postService.search(searchText, pageable);
        PageDto searchPageResponse = PageDto.of(searchPosts);
        model.addAttribute("posts", searchPageResponse);
        model.addAttribute("searchText", searchText);
        if(userDetails != null){
            model.addAttribute("nickname", userDetails.getNickname());
        }
        return "post/postSearch";
    }


    @GetMapping("/posts/write")
    public String addForm(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if(userDetails != null){
            model.addAttribute("nickname", userDetails.getNickname());
        }
        return "post/postForm";
    }

    @PostMapping("/posts")
    public String create(PostCreate postCreate, Authentication authentication, Model model) {
        String username = authentication.getName();
        postService.write(postCreate, username);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{postId}")
    public String edit(@PathVariable Long postId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        PostResponse postResponse = postService.get(postId);
        model.addAttribute("editPost", postResponse);
        if(userDetails != null){
            model.addAttribute("nickname", userDetails.getNickname());
        }
        if(userDetails.getUsername() != postResponse.getUsername()) return "/account/login"; // 작성자가 아니라는 페이지
        return "/post/postEdit";
    }

    @PutMapping("/posts/{postId}")
    public String editSave(@PathVariable Long postId, PostEdit postEdit) {
        postService.edit(postId, postEdit);
        return "redirect:/posts";
    }

    @DeleteMapping("/posts/{postId}")
    public String delete(@PathVariable Long postId) {
        postService.delete(postId);
        return "redirect:/posts";
    }
}
