package com.codestates.preproject.domain.comment.repository;

import com.codestates.preproject.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
