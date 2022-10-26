package com.codestates.preproject.domain.question.dto;

import com.codestates.preproject.domain.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QuestionResponseDto {
    private long questionId;
    private String title;
    private String body;
    private long memberId;
}
