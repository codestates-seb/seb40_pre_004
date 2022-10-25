package com.codestates.preproject.domain.answer.controller;

import com.codestates.preproject.domain.answer.dto.AnswerPatchDto;
import com.codestates.preproject.domain.answer.dto.AnswerPostDto;
import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.answer.mapper.AnswerMapper;
import com.codestates.preproject.domain.answer.service.AnswerService;
import com.codestates.preproject.page.PageResponseDto;
import com.codestates.preproject.page.PageInfo;
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
@RequestMapping("v1/answers")
@Validated
public class AnswerController {
        private final AnswerService answerService;
        private final AnswerMapper mapper;

        public AnswerController(AnswerService answerService, AnswerMapper mapper) {
                this.answerService = answerService;
                this.mapper = mapper;
        }

/*
        AnswerController 전체 흐름 구현(dto 클래스, 비즈니스 계층 미구현)
*/

        @PostMapping
        public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto postDto) {

                Answer answer = mapper.answerPostDtoToAnswer(postDto);
                Answer createdAnswer = answerService.createAnswer(answer);
                AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(createdAnswer);

                return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }

        @PatchMapping("/{answer-id}")
        public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                          @Valid @RequestBody AnswerPatchDto patchDto) {
                patchDto.setAnswerId(answerId);

                Answer answer = mapper.answerPatchDtoToAnswer(patchDto);
                Answer updatedAnswer = answerService.updateAnswer(answer);
                AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(updatedAnswer);

                return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

        @GetMapping("/{answer-id}")
        public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId) {

                Answer foundAnswer = answerService.findAnswer(answerId);
                AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(foundAnswer);

                return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity getAnswers(@RequestParam("page") @Positive int page,
                                         @RequestParam("size")  @Positive  int size) {
                PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("answerId").descending());

                Page<Answer> answerPage = answerService.findAnswers(pageRequest);
                List<AnswerResponseDto> responseDtos = mapper.answersToAnswerResponseDtos(answerPage.getContent());
                PageInfo pageInfo = new PageInfo(answerPage.getNumber() + 1, answerPage.getSize(), answerPage.getTotalElements(), answerPage.getTotalPages());
                PageResponseDto pageResponseDto = new PageResponseDto<>(responseDtos, pageInfo);

                return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
        }

        @DeleteMapping("/{answer-id}")
        public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {

                answerService.deleteAnswer(answerId);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
