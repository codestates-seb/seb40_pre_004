package com.codestates.preproject.domain.answer.controller;

import com.codestates.preproject.domain.answer.dto.AnswerPatchDto;
import com.codestates.preproject.domain.answer.dto.AnswerPostDto;
import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.answer.mapper.AnswerMapper;
import com.codestates.preproject.domain.answer.service.AnswerService;
import com.codestates.preproject.dto.MultiResponseDto;
import com.codestates.preproject.dto.PageInfo;
import com.codestates.preproject.dto.SingleResponseDto;
import lombok.NoArgsConstructor;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {
        private final AnswerService answerService;
        private final AnswerMapper mapper;

        public AnswerController(AnswerService answerService, AnswerMapper mapper) {
                this.answerService = answerService;
                this.mapper = mapper;
        }

        @PostMapping
        public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto postDto) {

                Answer answer = mapper.answerPostDtoToAnswer(postDto);
                Answer createdAnswer = answerService.createAnswer(answer);
                AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(createdAnswer);

                return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.CREATED);
        }

        @PatchMapping("/{answer-id}")
        public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                          @Valid @RequestBody AnswerPatchDto patchDto) {
                patchDto.setAnswerId(answerId);

                Answer answer = mapper.answerPatchDtoToAnswer(patchDto);
                Answer updatedAnswer = answerService.updateAnswer(answer);
                AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(updatedAnswer);

                return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.OK);
        }

        @GetMapping("/{answer-id}")
        public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId) {

                Answer foundAnswer = answerService.findAnswer(answerId);
                AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(foundAnswer);

                return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity getAnswers() {

                List<AnswerResponseDto> responseDtos = mapper.answersToAnswerResponseDtos(answerService.findAnswers());


                return new ResponseEntity<>(new SingleResponseDto<>(responseDtos),  HttpStatus.OK);
        }

        @DeleteMapping("/{answer-id}")
        public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {

                answerService.deleteAnswer(answerId);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
