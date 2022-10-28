package com.codestates.preproject.domain.question.entity;

import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.domain.comment.entity.Comment;
import com.codestates.preproject.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false, length = 10000)
    private String body;

    @ManyToOne()
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public long getMemberId() {
        return member.getMemberId();
    }

    public String getDisplayName() {
        return member.getDisplayName();
    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

//    public List<AnswerResponseDto> getAnswer() {
//        return answers.stream()
//                .map(answer -> new AnswerResponseDto(answer.getAnswerId(), answer.getBody(), answer.getAnswerCheck(),
//                        answer.getMemberDisplayName(), answer.getMemberId(), answer.getCreatedAt(), answer.getModifiedAt(),
//                        answer.getQuestionId(), answer.getComments()))
//                .collect(Collectors.toList());
//    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionHashtag> questionHashtags = new ArrayList<>();

    public void addAnswers(Answer answer) {
        answers.add(answer);
    }

    public void addQuestionHashtags(QuestionHashtag questionHashtag) {
        questionHashtags.add(questionHashtag);
    }

}
