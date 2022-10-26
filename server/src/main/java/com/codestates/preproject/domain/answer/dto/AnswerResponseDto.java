package com.codestates.preproject.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerResponseDto {
    private long answerId;
    private String body;
}
