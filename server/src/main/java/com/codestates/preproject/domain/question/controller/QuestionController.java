package com.codestates.preproject.domain.question.controller;

import com.codestates.preproject.domain.question.dto.QuestionPatchDto;
import com.codestates.preproject.domain.question.dto.QuestionPostDto;
import com.codestates.preproject.domain.question.dto.QuestionResponseDto;
import com.codestates.preproject.domain.question.entity.Question;
import com.codestates.preproject.domain.question.mapper.QuestionMapper;
import com.codestates.preproject.domain.question.service.QuestionService;
import com.codestates.preproject.page.PageInfo;
import com.codestates.preproject.page.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("v1/questions")
@Validated
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

/*
    QuestionController 전체 흐름 구현(dto 클래스, 비즈니스 계층 미구현)
*/

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionPostDto postDto) {

        Question question = mapper.questionPostDtoToQuestion(postDto);
        Question createdQuestion = questionService.createQuestion(question);
        QuestionResponseDto responseDto = mapper.questionToQuestionResponseDto(createdQuestion);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                        @Valid @RequestBody QuestionPatchDto patchDto) {
        patchDto.setQuestionId(questionId);

        Question question = mapper.questionPatchDtoToQuestion(patchDto);
        Question updatedQuestion = questionService.updateQuestion(question);
        QuestionResponseDto responseDto = mapper.questionToQuestionResponseDto(updatedQuestion);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId) {

        Question foundQuestion = questionService.findQuestion(questionId);
        QuestionResponseDto responseDto = mapper.questionToQuestionResponseDto(foundQuestion);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(@RequestParam("page") @Positive int page,
                                       @RequestParam("size") @Positive int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("questionId").descending());

        Page<Question> questionPage = questionService.findQuestions(pageRequest);
        List<QuestionResponseDto> responseDtos = mapper.questionsToQuestionResponseDtos(questionPage.getContent());
        PageInfo pageInfo = new PageInfo(questionPage.getNumber() + 1, questionPage.getSize(), questionPage.getTotalElements(), questionPage.getTotalPages());
        PageResponseDto pageResponseDto = new PageResponseDto<>(responseDtos, pageInfo);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId) {

        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}