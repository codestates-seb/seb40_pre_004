package com.codestates.preproject.domain.comment.service;

import com.codestates.preproject.domain.comment.entity.Comment;
import com.codestates.preproject.domain.comment.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return null;
    }

    public Comment updateComment(Comment comment) {
        return null;
    }

    public Comment findComment(long commentId) {
        return null;
    }

    public Page<Comment> findComments(Pageable pageable) {
        return null;
    }

    public void deleteComment(long commentId) {

    }
}
