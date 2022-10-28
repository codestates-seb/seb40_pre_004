package com.codestates.preproject.domain.answer.entity;

import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.domain.comment.dto.CommentResponseDto;
import com.codestates.preproject.domain.comment.entity.Comment;
import com.codestates.preproject.domain.question.entity.Question;
import com.codestates.preproject.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    @Column(nullable = false, length = 10000)
    private String body;

    @Column(nullable = false)
    private Boolean answerCheck = false;

    @ManyToOne()
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public String getMemberDisplayName() {
        return member.getDisplayName();
    }

    public long getMemberId() {
        return member.getMemberId();
    }

    @ManyToOne()
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public long getQuestionId() {
        return question.getQuestionId();
    }

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public List<CommentResponseDto> getComments() {
        return comments.stream()
                .map(comment -> new CommentResponseDto(comment.getCommentId(), comment.getBody(), comment.getDisplayName(),
                        comment.getCreatedAt(), comment.getModifiedAt()))
                .collect(Collectors.toList());
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
