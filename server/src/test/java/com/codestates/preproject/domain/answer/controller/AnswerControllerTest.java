
package com.codestates.preproject.domain.answer.controller;


import com.codestates.preproject.domain.answer.dto.AnswerPatchDto;
import com.codestates.preproject.domain.answer.dto.AnswerPostDto;
import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.answer.mapper.AnswerMapper;
import com.codestates.preproject.domain.answer.service.AnswerService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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



@WebMvcTest(AnswerController.class)
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
        AnswerPostDto answerPostDto = new AnswerPostDto("답변1");
        String content = gson.toJson(answerPostDto);
        AnswerResponseDto answerResponseDto = new AnswerResponseDto(1L, "답변1");

        given(mapper.answerPostDtoToAnswer(Mockito.any(AnswerPostDto.class)))
                .willReturn(new Answer());
        given(answerService.createAnswer(Mockito.any(Answer.class)))
                .willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
                .willReturn(answerResponseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/v1/answers")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.body").value(answerPostDto.getBody()))
                .andDo(document("post-answer",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답변 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("답변 내용")
                                )
                        )
                ));
    }

    @Test
    void patchAnswer() throws Exception {

        //given
        long answerId = 1L;
        AnswerPatchDto patchDto = new AnswerPatchDto();
        patchDto.setBody("답변1");

        String content = gson.toJson(patchDto);

        AnswerResponseDto responseDto = new AnswerResponseDto(1L, "답변1");

        given(mapper.answerPatchDtoToAnswer(Mockito.any(AnswerPatchDto.class)))
                .willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class)))
                .willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/v1/answers/{answer-id}", answerId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(answerId))
                .andExpect(jsonPath("$.data.body").value(patchDto.getBody()))
                .andDo(document("patch-answer",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자").ignored(),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답변 내용").optional()
                                )
                        ),
                        responseFields(
                                List.of(fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("답변 내용")
                                )
                        )
                ));
    }

    @Test
    void getAnswer() throws Exception{
        //given
        long answerId = 1L;

        AnswerResponseDto response = new AnswerResponseDto(answerId, "답변1");

        given(answerService.findAnswer(Mockito.anyLong()))
                .willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
                .willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/v1/answers/{answer-id}", answerId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(answerId))
                .andDo(document("get-answer",
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("answer-id").description("답변 식별자")),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("딥변 내용")
                                )
                        )
                ));
    }

    @Test
    void getAnswers() throws Exception{
        //given
        List<Answer> answers = List.of(new Answer(), new Answer(), new Answer());

        given(answerService.findAnswers())
                .willReturn(new ArrayList<Answer>());

        given(mapper.answersToAnswerResponseDtos(Mockito.anyList()))
                .willReturn(List.of(
                        new AnswerResponseDto(1L, "답변1"),
                        new AnswerResponseDto(2L, "답변2"),
                        new AnswerResponseDto(3L, "답변3")
                ));

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/v1/answers")
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-answers",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("답변 내용")
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
                        delete("/v1/answers/{answer-id}", answerId)
                );

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-answer",
                        pathParameters(parameterWithName("answer-id").description("답변 식별자"))
                ));
    }
}
