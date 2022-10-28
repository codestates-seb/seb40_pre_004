package com.codestates.preproject.domain.comment.dto;

import com.codestates.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentPatchDto {
    private long commentId;

    @NotSpace
    private String body;
}
