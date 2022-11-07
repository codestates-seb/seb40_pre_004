package com.codestates.preproject.domain.question.dto;

import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.answer.entity.Answer;
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
public class QuestionResponseDto {
    private long questionId;
    private String title;
    private String body;
    private long memberId;
    private String displayName;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private long answerCount;
    private long views;
}
