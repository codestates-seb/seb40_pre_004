package com.codestates.preproject.domain.comment.dto;

import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentPostDto {
    @Positive
    private long memberId;
    @Positive
    private long answerId;

    @NotBlank
    private String body;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

    public Answer getAnswer() {
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        return answer;
    }
}