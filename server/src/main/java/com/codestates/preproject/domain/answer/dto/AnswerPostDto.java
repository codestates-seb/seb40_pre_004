package com.codestates.preproject.domain.answer.dto;

import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerPostDto {
    @NotBlank
    private String body;

    @Positive
    private long questionId;

    @Positive
    private long memberId;

    public Question getQuestion() {
        Question question = new Question();
        question.setQuestionId(questionId);

        return question;
    }

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);

        return member;
    }
}
