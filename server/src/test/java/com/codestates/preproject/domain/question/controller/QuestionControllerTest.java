package com.codestates.preproject.domain.question.controller;

import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.comment.dto.CommentResponseDto;
import com.codestates.preproject.domain.question.controller.QuestionController;
import com.codestates.preproject.domain.question.dto.QuestionDetailsResponseDto;
import com.codestates.preproject.domain.question.dto.QuestionPatchDto;
import com.codestates.preproject.domain.question.dto.QuestionPostDto;
import com.codestates.preproject.domain.question.dto.QuestionResponseDto;
import com.codestates.preproject.domain.question.entity.Question;
import com.codestates.preproject.domain.question.mapper.QuestionMapper;
import com.codestates.preproject.domain.question.service.QuestionService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = QuestionController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@ActiveProfiles("local")
class QuestionControllerTest {
    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void postQuestion() throws Exception {
        //given
        QuestionPostDto postDto = new QuestionPostDto("??????1", "??????1", new ArrayList<>(Arrays.asList("??????", "?????????", "mysql")),1L);
        String content = gson.toJson(postDto);

        QuestionResponseDto responseDto = new QuestionResponseDto(1L, "??????1", "??????1",
                1L, "?????? ??????1", Arrays.asList("??????", "?????????", "mysql"), LocalDateTime.now(), LocalDateTime.now(), 1L);

        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionPostDto.class)))
                .willReturn(new Question());

        given(questionService.createQuestion(Mockito.any(Question.class)))
                .willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.title").value(postDto.getTitle()))
                .andExpect(jsonPath("$.data.body").value(postDto.getBody()))
                .andExpect(jsonPath("$.data.tags").value(postDto.getTags()))
                .andExpect(jsonPath("$.data.memberId").value(postDto.getMemberId()))
                .andDo(document("post-question",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("?????? ??????").optional(),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("?????? ??????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("?????? ??????")
                                )
                        )
                ));

    }

    @Test
    void patchQuestion() throws Exception {
        //given
        LocalDateTime time = LocalDateTime.now();
        long questionId = 1L;

        QuestionPatchDto patchDto = new QuestionPatchDto();
        patchDto.setTitle("?????? ??????");
        patchDto.setBody("?????? ??????");
        patchDto.setTags(new ArrayList<>(Arrays.asList("jpa", "orm")));

        String content = gson.toJson(patchDto);

        QuestionResponseDto responseDto = new QuestionResponseDto(1L, "?????? ??????", "?????? ??????",
                1L, "?????? ??????1", Arrays.asList("jpa", "orm"),time, LocalDateTime.now(), 1L);

        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionPatchDto.class)))
                .willReturn(new Question());

        given(questionService.updateQuestion(Mockito.any(Question.class)))
                .willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/questions/{question-id}", questionId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(questionId))
                .andExpect(jsonPath("$.data.title").value(patchDto.getTitle()))
                .andExpect(jsonPath("$.data.body").value(patchDto.getBody()))
                .andExpect(jsonPath("$.data.tags").value(patchDto.getTags()))
                .andDo(document("patch-question",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("question-id").description("?????? ?????????")),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("?????? ?????????").ignored(),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("?????? ??????").optional(),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("?????? ??????").optional(),
                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("?????? ??????").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("?????? ??????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("?????? ??????")
                                )
                        )
                ));
    }

    @Test
    void getQuestion() throws Exception {
        //given
        long questionId = 1L;

        QuestionDetailsResponseDto responseDto = new QuestionDetailsResponseDto(1L, "??????1", "??????1",
                1L, "?????? ??????1", Arrays.asList("jpa", "orm"),LocalDateTime.now(), LocalDateTime.now(),
                List.of(new AnswerResponseDto(1L, "?????? ??????", false,
                        "?????????1", 1L, LocalDateTime.now(), LocalDateTime.now(), 1L,
                        List.of(new CommentResponseDto(1L, "??????1", "?????????1", LocalDateTime.now(), LocalDateTime.now(),1L),
                                new CommentResponseDto(2L, "??????2", "?????????1", LocalDateTime.now(), LocalDateTime.now(), 1L),
                                new CommentResponseDto(3L, "??????3", "?????????2", LocalDateTime.now(), LocalDateTime.now(), 2L)))));

        given(questionService.findQuestion(Mockito.anyLong()))
                .willReturn(new Question());

        given(mapper.questionToQuestionDetailsResponseDto(Mockito.any(Question.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/{question-id}", questionId)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(questionId))
                .andDo(document("get-question",
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("question-id").description("?????? ?????????")),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("?????? ??????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("?????? ??????"),

                                        fieldWithPath("data.answers").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.answers[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.answers[].answerCheck").type(JsonFieldType.BOOLEAN).description("?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].memberDisplayName").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????? ?????????"),
                                        fieldWithPath("data.answers[].createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),

                                        fieldWithPath("data.answers[].comments").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].comments[].commentId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.answers[].comments[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.answers[].comments[].displayName").type(JsonFieldType.STRING).description("?????? ?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].comments[].createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].comments[].modifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.answers[].comments[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????")
                                )
                        )
                ));
    }

    @Test
    void getQuestions() throws Exception {
        //given
        int page = 1;
        int size = 5;

        List<Question> questions = List.of(new Question(), new Question(), new Question());

        List<QuestionResponseDto> responseDtos = List.of(
                new QuestionResponseDto(3L, "??????3", "??????3", 2L, "?????? ??????2", Arrays.asList("java", "spring", "mysql"),LocalDateTime.now(), LocalDateTime.now(), 1L),
                new QuestionResponseDto(2L, "??????2", "??????2", 1L, "?????? ??????1", Arrays.asList("jpa", "orm"),LocalDateTime.now(), LocalDateTime.now(), 2L),
                new QuestionResponseDto(1L, "??????1", "??????1", 1L, "?????? ??????1", Arrays.asList("html", "css", "js"),LocalDateTime.now(), LocalDateTime.now(), 1L)
        );

        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyInt()))
                .willReturn(new PageImpl<>(questions, PageRequest.of(page-1, size, Sort.by("questionId").descending()), questions.size()));

        given(mapper.questionsToQuestionResponseDtos(Mockito.anyList()))
                .willReturn(responseDtos);

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions?page={page}&size={size}", page, size)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-questions",
                        preprocessResponse(prettyPrint()),
                        requestParameters(List.of(
                                parameterWithName("page").description("????????? ??????"),
                                parameterWithName("size").description("????????? ??????"))),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data[].displayName").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].tags").type(JsonFieldType.ARRAY).description("?????? ??????"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].answerCount").type(JsonFieldType.NUMBER).description("?????? ??????"),

                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("????????? ??????"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("??? ??????"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???")
                                )
                        )
                ));
    }

    @Test
    void deleteQuestion() throws Exception {
        //given
        long questionId = 1L;

        //when
        ResultActions actions = mockMvc.perform(
                delete("/questions/{question-id}", questionId)
        );

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-question",
                        pathParameters(parameterWithName("question-id").description("?????? ?????????"))
                ));
    }
}
