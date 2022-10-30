package com.codestates.preproject.domain.question.dto;

import com.codestates.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class QuestionPatchDto {
    private long questionId;

    @NotSpace
    private String title;

    @NotSpace
    private String body;

    private List<String> tags;
}
