package com.codestates.preproject.domain.answer.entity;

import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.domain.comment.entity.Comment;
import com.codestates.preproject.domain.question.entity.Question;
import com.codestates.preproject.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private boolean answerCheck = false;

    @ManyToOne()
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne()
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();
}
