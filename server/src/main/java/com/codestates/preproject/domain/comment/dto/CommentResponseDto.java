package com.codestates.preproject.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDto {
    private long commentId;
    private String body;
    private String displayName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
