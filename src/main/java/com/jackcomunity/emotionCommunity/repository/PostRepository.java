package com.jackcomunity.emotionCommunity.repository;

import com.jackcomunity.emotionCommunity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
