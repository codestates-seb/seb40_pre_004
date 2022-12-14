
package com.codestates.preproject.domain.answer.controller;


import com.codestates.preproject.domain.answer.dto.AnswerPatchDto;
import com.codestates.preproject.domain.answer.dto.AnswerPostDto;
import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.answer.mapper.AnswerMapper;
import com.codestates.preproject.domain.answer.service.AnswerService;
import com.codestates.preproject.domain.comment.dto.CommentResponseDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(value = AnswerController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@ActiveProfiles("local")
class AnswerControllerTest {

    @MockBean
    private AnswerMapper mapper;

    @MockBean
    private AnswerService answerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;


    @Test
    void postAnswer()  throws Exception {
        //given
        AnswerPostDto answerPostDto = new AnswerPostDto("??????1", 1L, 2L);
        String content = gson.toJson(answerPostDto);
        AnswerResponseDto answerResponseDto =
                new AnswerResponseDto(1L, "??????1", false, "?????????2", 2L,
                        LocalDateTime.now(), LocalDateTime.now(), 1L,
                        List.of(new CommentResponseDto(1L, "??????1", "?????????1", LocalDateTime.now(), LocalDateTime.now(), 1L),
                                new CommentResponseDto(2L, "??????2", "?????????1", LocalDateTime.now(), LocalDateTime.now(), 1L),
                                new CommentResponseDto(3L, "??????3", "?????????2", LocalDateTime.now(), LocalDateTime.now(), 2L)));

        given(mapper.answerPostDtoToAnswer(Mockito.any(AnswerPostDto.class)))
                .willReturn(new Answer());
        given(answerService.createAnswer(Mockito.any(Answer.class)))
                .willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
                .willReturn(answerResponseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/answers")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.body").value(answerPostDto.getBody()))
                .andExpect(jsonPath("$.data.questionId").value(answerPostDto.getQuestionId()))
                .andExpect(jsonPath("$.data.memberId").value(answerPostDto.getMemberId()))
                .andDo(document("post-answer",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.answerCheck").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????"),
                                        fieldWithPath("data.memberDisplayName").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),

                                        fieldWithPath("data.comments").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].commentId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.comments[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.comments[].displayName").type(JsonFieldType.STRING).description("?????? ?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????")
                                )
                        )
                ));
    }

    @Test
    void patchAnswer() throws Exception {

        //given
        long answerId = 1L;
        AnswerPatchDto patchDto = new AnswerPatchDto();
        patchDto.setBody("??????1");
        patchDto.setAnswerCheck(true);

        String content = gson.toJson(patchDto);

        AnswerResponseDto responseDto =
                new AnswerResponseDto(1L, "??????1", true, "?????????2", 2L,
                        LocalDateTime.now(), LocalDateTime.now(), 1L,
                        List.of(new CommentResponseDto(1L, "??????1", "?????????1", LocalDateTime.now(), LocalDateTime.now(), 1L),
                                new CommentResponseDto(2L, "??????2", "?????????1", LocalDateTime.now(), LocalDateTime.now(), 1L),
                                new CommentResponseDto(3L, "??????3", "?????????2", LocalDateTime.now(), LocalDateTime.now(), 2L)));

        given(mapper.answerPatchDtoToAnswer(Mockito.any(AnswerPatchDto.class)))
                .willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class)))
                .willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/answers/{answer-id}", answerId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(answerId))
                .andExpect(jsonPath("$.data.body").value(patchDto.getBody()))
                .andExpect(jsonPath("$.data.answerCheck").value(patchDto.getAnswerCheck()))
                .andDo(document("patch-answer",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("answer-id").description("?????? ?????????")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("?????? ?????????").ignored(),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("?????? ??????").optional(),
                                        fieldWithPath("answerCheck").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.answerCheck").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????"),
                                        fieldWithPath("data.memberDisplayName").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),

                                        fieldWithPath("data.comments").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].commentId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.comments[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.comments[].displayName").type(JsonFieldType.STRING).description("?????? ?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????")
                                )
                        )
                ));
    }

    @Test
    void getAnswer() throws Exception{
        //given
        long answerId = 1L;

        AnswerResponseDto responseDto =
                new AnswerResponseDto(1L, "??????1", true, "?????????2", 2L,
                        LocalDateTime.now(), LocalDateTime.now(), 1L,
                        List.of(new CommentResponseDto(1L, "??????1", "?????????1", LocalDateTime.now(), LocalDateTime.now(),1L),
                                new CommentResponseDto(2L, "??????2", "?????????1", LocalDateTime.now(), LocalDateTime.now(), 1L),
                                new CommentResponseDto(3L, "??????3", "?????????2", LocalDateTime.now(), LocalDateTime.now(), 2L)));

        given(answerService.findAnswer(Mockito.anyLong()))
                .willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/answers/{answer-id}", answerId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(answerId))
                .andDo(document("get-answer",
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("answer-id").description("?????? ?????????")),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.answerCheck").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????"),
                                        fieldWithPath("data.memberDisplayName").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),

                                        fieldWithPath("data.comments").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].commentId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.comments[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.comments[].displayName").type(JsonFieldType.STRING).description("?????? ?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.comments[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????")
                                )
                        )
                ));
    }

    @Test
    void getAnswers() throws Exception{
        //given
        List<Answer> answers = List.of(new Answer(), new Answer(), new Answer());

        given(answerService.findAnswers())
                .willReturn(answers);

        given(mapper.answersToAnswerResponseDtos(Mockito.anyList()))
                .willReturn(List.of(
                        new AnswerResponseDto(1L, "??????1", true, "?????????2", 2L, LocalDateTime.now(), LocalDateTime.now(), 1L,
                                List.of(new CommentResponseDto(1L, "??????1", "?????????1", LocalDateTime.now(), LocalDateTime.now(),1L),
                                        new CommentResponseDto(2L, "??????2", "?????????1", LocalDateTime.now(), LocalDateTime.now(),1L),
                                        new CommentResponseDto(3L, "??????3", "?????????2", LocalDateTime.now(), LocalDateTime.now(), 2L))),
                        new AnswerResponseDto(2L, "??????2", false, "?????????3", 3L, LocalDateTime.now(), LocalDateTime.now(), 1L,
                                List.of(new CommentResponseDto(4L, "??????4", "?????????1", LocalDateTime.now(), LocalDateTime.now(),2L),
                                        new CommentResponseDto(5L, "??????5", "?????????1", LocalDateTime.now(), LocalDateTime.now(),3L),
                                        new CommentResponseDto(6L, "??????6", "?????????2", LocalDateTime.now(), LocalDateTime.now(),3L))),
                        new AnswerResponseDto(3L, "??????3", false, "?????????3", 3L, LocalDateTime.now(), LocalDateTime.now(), 2L,
                                List.of(new CommentResponseDto(7L, "??????7", "?????????1", LocalDateTime.now(), LocalDateTime.now(),1L),
                                        new CommentResponseDto(8L, "??????8", "?????????1", LocalDateTime.now(), LocalDateTime.now(),1L),
                                        new CommentResponseDto(9L, "??????9", "?????????2", LocalDateTime.now(), LocalDateTime.now(),2L)))
                ));

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/answers")
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-answers",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].answerCheck").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].memberDisplayName").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),

                                        fieldWithPath("data[].comments").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].comments[].commentId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data[].comments[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].comments[].displayName").type(JsonFieldType.STRING).description("?????? ?????? ?????? ??????"),
                                        fieldWithPath("data[].comments[].createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].comments[].modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].comments[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????")
                                )
                        )
                ));
    }

    @Test
    void deleteAnswer() throws Exception{
        //given
        long answerId = 1L;

        //when
        ResultActions actions =
                mockMvc.perform(
                        delete("/answers/{answer-id}", answerId)
                );

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-answer",
                        pathParameters(parameterWithName("answer-id").description("?????? ?????????"))
                ));
    }
}
