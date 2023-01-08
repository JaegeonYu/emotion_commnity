package com.jackcomunity.emotionCommunity.repository;

import com.jackcomunity.emotionCommunity.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
