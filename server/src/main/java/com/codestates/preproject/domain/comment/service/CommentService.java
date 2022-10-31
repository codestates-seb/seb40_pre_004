package com.codestates.preproject.domain.comment.service;

import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.answer.service.AnswerService;
import com.codestates.preproject.domain.comment.entity.Comment;
import com.codestates.preproject.domain.comment.repository.CommentRepository;
import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.service.MemberService;
import com.codestates.preproject.exception.BusinessLogicException;
import com.codestates.preproject.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private AnswerService answerService;

    public CommentService(CommentRepository commentRepository, MemberService memberService, AnswerService answerService) {
        this.commentRepository = commentRepository;
        this.memberService = memberService;
        this.answerService = answerService;
    }

    public Comment createComment(Comment comment) {
        Answer answer = verifyExistAnswer(comment.getAnswer());
        Member member = verifyExistsMember(comment.getMember());

        comment.setAnswer(answer);
        comment.setMember(member);

        answer.addComment(comment);
        member.addComment(comment);

        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());

        Optional.ofNullable(comment.getBody())
                .ifPresent(body -> findComment.setBody(body));

        return commentRepository.save(findComment);
    }

    public Comment findComment(long commentId) {
        return findVerifiedComment(commentId);
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

    private Comment findVerifiedComment(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        return comment;
    }

    private Member verifyExistsMember(Member member) {
        return memberService.findVerifiedMember(member.getMemberId());
    }

    private Answer verifyExistAnswer(Answer answer) {
        return answerService.findVerifiedAnswer(answer.getAnswerId());
    }
}
