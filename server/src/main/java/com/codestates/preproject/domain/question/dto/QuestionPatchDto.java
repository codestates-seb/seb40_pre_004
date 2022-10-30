package com.codestates.preproject.domain.question.dto;

import com.codestates.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionPatchDto {
    private long questionId;

    @NotSpace
    private String title;

    @NotSpace
    private String body;
}
