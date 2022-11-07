package com.codestates.preproject.domain.answer.dto;

import com.codestates.preproject.domain.comment.dto.CommentResponseDto;
import com.codestates.preproject.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponseDto {
    private long answerId;
    private String body;
    private Boolean answerCheck;
    private String memberDisplayName;
    private long memberId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private long questionId;
    private List<CommentResponseDto> comments;
}
