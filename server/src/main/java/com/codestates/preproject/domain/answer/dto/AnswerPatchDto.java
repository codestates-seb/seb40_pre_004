package com.codestates.preproject.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerPatchDto {
    private long answerId;
    private String body;
}
