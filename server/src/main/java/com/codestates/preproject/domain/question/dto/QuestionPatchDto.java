package com.codestates.preproject.domain.question.dto;

import com.codestates.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

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

    @Nullable
    private List<@NotBlank String> tags;
}
