package com.codestates.preproject.domain.question.dto;

import com.codestates.preproject.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class QuestionPostDto {
    @NotBlank
    private String title;

    @NotBlank
    private String body;

    @Positive
    private long memberId;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);

        return member;
    }
}
