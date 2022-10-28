//package com.codestates.preproject.domain.question.controller;
//
//import com.codestates.preproject.domain.question.controller.QuestionController;
//import com.codestates.preproject.domain.question.dto.QuestionPatchDto;
//import com.codestates.preproject.domain.question.dto.QuestionPostDto;
//import com.codestates.preproject.domain.question.dto.QuestionResponseDto;
//import com.codestates.preproject.domain.question.entity.Question;
//import com.codestates.preproject.domain.question.mapper.QuestionMapper;
//import com.codestates.preproject.domain.question.service.QuestionService;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(QuestionController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//@ActiveProfiles("local")
//class QuestionControllerTest {
//    @MockBean
//    private QuestionService questionService;
//
//    @MockBean
//    private QuestionMapper mapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    void postQuestion() throws Exception {
//        //given
//        QuestionPostDto postDto = new QuestionPostDto("제목1", "본문1", 1L);
//        String content = gson.toJson(postDto);
//
//        QuestionResponseDto responseDto = new QuestionResponseDto(1L, "제목1", "본문1", 1L);
//
//        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionPostDto.class)))
//                .willReturn(new Question());
//
//        given(questionService.createQuestion(Mockito.any(Question.class)))
//                .willReturn(new Question());
//
//        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class)))
//                .willReturn(responseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                post("/v1/questions")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//        );
//
//        //then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.title").value(postDto.getTitle()))
//                .andExpect(jsonPath("$.data.body").value(postDto.getBody()))
//                .andExpect(jsonPath("$.data.memberId").value(postDto.getMemberId()))
//                .andDo(document("post-question",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
//                                        fieldWithPath("body").type(JsonFieldType.STRING).description("질문 본문"),
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("질문 제목"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("질문 본문"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
//                                )
//                        )
//                ));
//
//    }
//
//    @Test
//    void patchQuestion() throws Exception {
//        //given
//        long questionId = 1L;
//
//        QuestionPatchDto patchDto = new QuestionPatchDto();
//        patchDto.setTitle("제목 수정");
//        patchDto.setBody("본문 수정");
//
//        String content = gson.toJson(patchDto);
//
//        QuestionResponseDto responseDto = new QuestionResponseDto(1L, "제목 수정", "본문 수정", 1L);
//
//        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionPatchDto.class)))
//                .willReturn(new Question());
//
//        given(questionService.updateQuestion(Mockito.any(Question.class)))
//                .willReturn(new Question());
//
//        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class)))
//                .willReturn(responseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                patch("/v1/questions/{question-id}", questionId)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//        );
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.questionId").value(questionId))
//                .andExpect(jsonPath("$.data.title").value(patchDto.getTitle()))
//                .andExpect(jsonPath("$.data.body").value(patchDto.getBody()))
//                .andDo(document("patch-question",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(parameterWithName("question-id").description("질문 식별자")),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자").ignored(),
//                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목").optional(),
//                                        fieldWithPath("body").type(JsonFieldType.STRING).description("질문 본문").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("질문 제목"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("질문 본문"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void getQuestion() throws Exception {
//        //given
//        long questionId = 1L;
//
//        QuestionResponseDto responseDto = new QuestionResponseDto(1L, "제목1", "본문1", 1L);
//
//        given(questionService.findQuestion(Mockito.anyLong()))
//                .willReturn(new Question());
//
//        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class)))
//                .willReturn(responseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                get("/v1/questions/{question-id}", questionId)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.questionId").value(questionId))
//                .andDo(document("get-question",
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(parameterWithName("question-id").description("질문 식별자")),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("질문 제목"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("질문 본문"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void getQuestions() throws Exception {
//        //given
//        int page = 1;
//        int size = 5;
//
//        List<Question> questions = List.of(new Question(), new Question(), new Question());
//
//        List<QuestionResponseDto> responseDtos = List.of(
//                new QuestionResponseDto(3L, "제목3", "본문3", 2L),
//                new QuestionResponseDto(2L, "제목2", "본문2", 1L),
//                new QuestionResponseDto(1L, "제목1", "본문1", 1L)
//        );
//
//        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyInt()))
//                .willReturn(new PageImpl<>(questions, PageRequest.of(page-1, size, Sort.by("questionId").descending()), questions.size()));
//
//        given(mapper.questionsToQuestionResponseDtos(Mockito.anyList()))
//                .willReturn(responseDtos);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                get("/v1/questions?page={page}&size={size}", page, size)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray())
//                .andDo(document("get-questions",
//                        preprocessResponse(prettyPrint()),
//                        requestParameters(List.of(
//                                parameterWithName("page").description("페이지 번호"),
//                                parameterWithName("size").description("페이지 크기"))),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
//                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("질문 제목"),
//                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("질문 본문"),
//                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//
//                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
//                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호"),
//                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 크기"),
//                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 갯수"),
//                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void deleteQuestion() throws Exception {
//        //given
//        long questionId = 1L;
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                delete("/v1/questions/{question-id}", questionId)
//        );
//
//        //then
//        actions
//                .andExpect(status().isNoContent())
//                .andDo(document("delete-question",
//                        pathParameters(parameterWithName("question-id").description("질문 식별자"))
//                ));
//    }
//}
