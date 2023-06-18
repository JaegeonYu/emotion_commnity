package com.jackcomunity.emotionCommunity.repository;

import com.jackcomunity.emotionCommunity.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = "comments")
    Optional<Post> findPostWithCommentsById(Long id);

    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
