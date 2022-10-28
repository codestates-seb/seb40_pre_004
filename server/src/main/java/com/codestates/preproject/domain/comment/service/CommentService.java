package com.codestates.preproject.domain.comment.service;

import com.codestates.preproject.domain.comment.entity.Comment;
import com.codestates.preproject.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository; }

    public Comment createComment(Comment comment) {
        //answerService.findByAnswer(comment.getAnswer().getAnswerId());
        return saveComment(comment);
    }

    public Comment updateComment(Comment comment) {
        Comment findComment = findByComment(comment.getCommentId());

        Optional.ofNullable(comment.getBody())
                .ifPresent(findComment::setBody);
        return saveComment(findComment);
    }

    public Comment findComment(long commentId) {
        return findByComment(commentId);
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

    private Comment findByComment(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("코멘트가 등록되지 않았습니다."));
        return comment;
    }

    private Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }
}
