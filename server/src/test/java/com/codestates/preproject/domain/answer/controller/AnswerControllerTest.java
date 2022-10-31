//
//package com.codestates.preproject.domain.answer.controller;
//
//
//import com.codestates.preproject.domain.answer.dto.AnswerPatchDto;
//import com.codestates.preproject.domain.answer.dto.AnswerPostDto;
//import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
//import com.codestates.preproject.domain.answer.entity.Answer;
//import com.codestates.preproject.domain.answer.mapper.AnswerMapper;
//import com.codestates.preproject.domain.answer.service.AnswerService;
//import com.codestates.preproject.domain.comment.dto.CommentResponseDto;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
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
//
//
//@WebMvcTest(AnswerController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//@ActiveProfiles("local")
//class AnswerControllerTest {
//
//    @MockBean
//    private AnswerMapper mapper;
//
//    @MockBean
//    private AnswerService answerService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//
//    @Test
//    void postAnswer()  throws Exception {
//        //given
//        AnswerPostDto answerPostDto = new AnswerPostDto("답변1", 1L, 2L);
//        String content = gson.toJson(answerPostDto);
//        AnswerResponseDto answerResponseDto =
//                new AnswerResponseDto(1L, "답변1", false, "홍길동2", 2L,
//                        LocalDateTime.now(), LocalDateTime.now(), 1L,
//                        List.of(new CommentResponseDto(1L, "댓글1", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L),
//                                new CommentResponseDto(2L, "댓글2", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L),
//                                new CommentResponseDto(3L, "댓글3", "홍길동2", LocalDateTime.now(), LocalDateTime.now(), 2L)));
//
//        given(mapper.answerPostDtoToAnswer(Mockito.any(AnswerPostDto.class)))
//                .willReturn(new Answer());
//        given(answerService.createAnswer(Mockito.any(Answer.class)))
//                .willReturn(new Answer());
//        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
//                .willReturn(answerResponseDto);
//
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/v1/answers")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.body").value(answerPostDto.getBody()))
//                .andExpect(jsonPath("$.data.questionId").value(answerPostDto.getQuestionId()))
//                .andExpect(jsonPath("$.data.memberId").value(answerPostDto.getMemberId()))
//                .andDo(document("post-answer",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답변 내용"),
//                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("답변 내용"),
//                                        fieldWithPath("data.answerCheck").type(JsonFieldType.BOOLEAN).description("답변 채택 여부"),
//                                        fieldWithPath("data.memberDisplayName").type(JsonFieldType.STRING).description("답변 회원 이름"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("답변 회원 식별자"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성 날짜"),
//                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정 날짜"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//
//                                        fieldWithPath("data.comments").type(JsonFieldType.ARRAY).description("답변 댓글 정보"),
//                                        fieldWithPath("data.comments[].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
//                                        fieldWithPath("data.comments[].body").type(JsonFieldType.STRING).description("댓글 내용"),
//                                        fieldWithPath("data.comments[].displayName").type(JsonFieldType.STRING).description("댓글 작성 회원 이름"),
//                                        fieldWithPath("data.comments[].createdAt").type(JsonFieldType.STRING).description("댓글 생성 시간"),
//                                        fieldWithPath("data.comments[].modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 시간"),
//                                        fieldWithPath("data.comments[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void patchAnswer() throws Exception {
//
//        //given
//        long answerId = 1L;
//        AnswerPatchDto patchDto = new AnswerPatchDto();
//        patchDto.setBody("답변1");
//        patchDto.setAnswerCheck(true);
//
//        String content = gson.toJson(patchDto);
//
//        AnswerResponseDto responseDto =
//                new AnswerResponseDto(1L, "답변1", true, "홍길동2", 2L,
//                        LocalDateTime.now(), LocalDateTime.now(), 1L,
//                        List.of(new CommentResponseDto(1L, "댓글1", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L),
//                                new CommentResponseDto(2L, "댓글2", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L),
//                                new CommentResponseDto(3L, "댓글3", "홍길동2", LocalDateTime.now(), LocalDateTime.now(), 2L)));
//
//        given(mapper.answerPatchDtoToAnswer(Mockito.any(AnswerPatchDto.class)))
//                .willReturn(new Answer());
//        given(answerService.updateAnswer(Mockito.any(Answer.class)))
//                .willReturn(new Answer());
//        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
//                .willReturn(responseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                patch("/v1/answers/{answer-id}", answerId)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//        );
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.answerId").value(answerId))
//                .andExpect(jsonPath("$.data.body").value(patchDto.getBody()))
//                .andExpect(jsonPath("$.data.answerCheck").value(patchDto.getAnswerCheck()))
//                .andDo(document("patch-answer",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(
//                                parameterWithName("answer-id").description("답변 식별자")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자").ignored(),
//                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답변 내용").optional(),
//                                        fieldWithPath("answerCheck").type(JsonFieldType.BOOLEAN).description("답변 채택 여부").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("답변 내용"),
//                                        fieldWithPath("data.answerCheck").type(JsonFieldType.BOOLEAN).description("답변 채택 여부"),
//                                        fieldWithPath("data.memberDisplayName").type(JsonFieldType.STRING).description("답변 회원 이름"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("답변 회원 식별자"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성 날짜"),
//                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정 날짜"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//
//                                        fieldWithPath("data.comments").type(JsonFieldType.ARRAY).description("답변 댓글 정보"),
//                                        fieldWithPath("data.comments[].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
//                                        fieldWithPath("data.comments[].body").type(JsonFieldType.STRING).description("댓글 내용"),
//                                        fieldWithPath("data.comments[].displayName").type(JsonFieldType.STRING).description("댓글 작성 회원 이름"),
//                                        fieldWithPath("data.comments[].createdAt").type(JsonFieldType.STRING).description("댓글 생성 시간"),
//                                        fieldWithPath("data.comments[].modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 시간"),
//                                        fieldWithPath("data.comments[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void getAnswer() throws Exception{
//        //given
//        long answerId = 1L;
//
//        AnswerResponseDto responseDto =
//                new AnswerResponseDto(1L, "답변1", true, "홍길동2", 2L,
//                        LocalDateTime.now(), LocalDateTime.now(), 1L,
//                        List.of(new CommentResponseDto(1L, "댓글1", "홍길동1", LocalDateTime.now(), LocalDateTime.now(),1L),
//                                new CommentResponseDto(2L, "댓글2", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L),
//                                new CommentResponseDto(3L, "댓글3", "홍길동2", LocalDateTime.now(), LocalDateTime.now(), 2L)));
//
//        given(answerService.findAnswer(Mockito.anyLong()))
//                .willReturn(new Answer());
//        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class)))
//                .willReturn(responseDto);
//
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        get("/v1/answers/{answer-id}", answerId)
//                                .accept(MediaType.APPLICATION_JSON)
//                );
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.answerId").value(answerId))
//                .andDo(document("get-answer",
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(parameterWithName("answer-id").description("답변 식별자")),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("답변 내용"),
//                                        fieldWithPath("data.answerCheck").type(JsonFieldType.BOOLEAN).description("답변 채택 여부"),
//                                        fieldWithPath("data.memberDisplayName").type(JsonFieldType.STRING).description("답변 회원 이름"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("답변 회원 식별자"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변 생성 날짜"),
//                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변 수정 날짜"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//
//                                        fieldWithPath("data.comments").type(JsonFieldType.ARRAY).description("답변 댓글 정보"),
//                                        fieldWithPath("data.comments[].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
//                                        fieldWithPath("data.comments[].body").type(JsonFieldType.STRING).description("댓글 내용"),
//                                        fieldWithPath("data.comments[].displayName").type(JsonFieldType.STRING).description("댓글 작성 회원 이름"),
//                                        fieldWithPath("data.comments[].createdAt").type(JsonFieldType.STRING).description("댓글 생성 시간"),
//                                        fieldWithPath("data.comments[].modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 시간"),
//                                        fieldWithPath("data.comments[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void getAnswers() throws Exception{
//        //given
//        List<Answer> answers = List.of(new Answer(), new Answer(), new Answer());
//
//        given(answerService.findAnswers())
//                .willReturn(answers);
//
//        given(mapper.answersToAnswerResponseDtos(Mockito.anyList()))
//                .willReturn(List.of(
//                        new AnswerResponseDto(1L, "답변1", true, "홍길동2", 2L, LocalDateTime.now(), LocalDateTime.now(), 1L,
//                                List.of(new CommentResponseDto(1L, "댓글1", "홍길동1", LocalDateTime.now(), LocalDateTime.now(),1L),
//                                        new CommentResponseDto(2L, "댓글2", "홍길동1", LocalDateTime.now(), LocalDateTime.now(),1L),
//                                        new CommentResponseDto(3L, "댓글3", "홍길동2", LocalDateTime.now(), LocalDateTime.now(), 2L))),
//                        new AnswerResponseDto(2L, "답변2", false, "홍길동3", 3L, LocalDateTime.now(), LocalDateTime.now(), 1L,
//                                List.of(new CommentResponseDto(4L, "댓글4", "홍길동1", LocalDateTime.now(), LocalDateTime.now(),2L),
//                                        new CommentResponseDto(5L, "댓글5", "홍길동1", LocalDateTime.now(), LocalDateTime.now(),3L),
//                                        new CommentResponseDto(6L, "댓글6", "홍길동2", LocalDateTime.now(), LocalDateTime.now(),3L))),
//                        new AnswerResponseDto(3L, "답변3", false, "홍길동3", 3L, LocalDateTime.now(), LocalDateTime.now(), 2L,
//                                List.of(new CommentResponseDto(7L, "댓글7", "홍길동1", LocalDateTime.now(), LocalDateTime.now(),1L),
//                                        new CommentResponseDto(8L, "댓글8", "홍길동1", LocalDateTime.now(), LocalDateTime.now(),1L),
//                                        new CommentResponseDto(9L, "댓글9", "홍길동2", LocalDateTime.now(), LocalDateTime.now(),2L)))
//                ));
//
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        get("/v1/answers")
//                                .accept(MediaType.APPLICATION_JSON)
//                );
//
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray())
//                .andDo(document("get-answers",
//                        preprocessResponse(prettyPrint()),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
//                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
//                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("답변 내용"),
//                                        fieldWithPath("data[].answerCheck").type(JsonFieldType.BOOLEAN).description("답변 채택 여부"),
//                                        fieldWithPath("data[].memberDisplayName").type(JsonFieldType.STRING).description("답변 회원 이름"),
//                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("답변 회원 식별자"),
//                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("답변 생성 날짜"),
//                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 날짜"),
//                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
//
//                                        fieldWithPath("data[].comments").type(JsonFieldType.ARRAY).description("답변 댓글 정보"),
//                                        fieldWithPath("data[].comments[].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
//                                        fieldWithPath("data[].comments[].body").type(JsonFieldType.STRING).description("댓글 내용"),
//                                        fieldWithPath("data[].comments[].displayName").type(JsonFieldType.STRING).description("댓글 작성 회원 이름"),
//                                        fieldWithPath("data[].comments[].createdAt").type(JsonFieldType.STRING).description("댓글 생성 시간"),
//                                        fieldWithPath("data[].comments[].modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 시간"),
//                                        fieldWithPath("data[].comments[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void deleteAnswer() throws Exception{
//        //given
//        long answerId = 1L;
//
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        delete("/v1/answers/{answer-id}", answerId)
//                );
//
//        //then
//        actions
//                .andExpect(status().isNoContent())
//                .andDo(document("delete-answer",
//                        pathParameters(parameterWithName("answer-id").description("답변 식별자"))
//                ));
//    }
//}
