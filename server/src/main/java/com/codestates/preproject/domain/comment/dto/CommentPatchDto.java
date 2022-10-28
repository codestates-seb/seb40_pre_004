package com.codestates.preproject.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentPatchDto {
    private long commentId;

    @NotBlank
    private String body;
}
