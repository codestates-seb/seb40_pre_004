//package com.codestates.preproject.domain.comment.controller;
//
//import com.codestates.preproject.domain.comment.controller.CommentController;
//import com.codestates.preproject.domain.comment.dto.CommentPatchDto;
//import com.codestates.preproject.domain.comment.dto.CommentPostDto;
//import com.codestates.preproject.domain.comment.dto.CommentResponseDto;
//import com.codestates.preproject.domain.comment.entity.Comment;
//import com.codestates.preproject.domain.comment.mapper.CommentMapper;
//import com.codestates.preproject.domain.comment.service.CommentService;
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
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CommentController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//@ActiveProfiles("local")
//class CommentControllerTest {
//
//    @MockBean
//    private CommentService commentService;
//
//    @MockBean
//    private CommentMapper mapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    void postComment() throws Exception {
//        //given
//        CommentPostDto postDto = new CommentPostDto(1L, 1L, "댓글1");
//        String content = gson.toJson(postDto);
//
//        CommentResponseDto responseDto = new CommentResponseDto(1L, "댓글1", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L);
//
//        given(mapper.commentPostDtoToComment(Mockito.any(CommentPostDto.class)))
//                .willReturn(new Comment());
//
//        given(commentService.createComment(Mockito.any(Comment.class)))
//                .willReturn(new Comment());
//
//        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class)))
//                .willReturn(responseDto);
//
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/v1/comments")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        //then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.body").value(postDto.getBody()))
//                .andDo(document("post-comment",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
//                                        fieldWithPath("body").type(JsonFieldType.STRING).description("댓글 내용")
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("댓글 내용"),
//                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("댓글 작성 회원 이름"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("댓글 생성 날짜"),
//                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 날짜"),
//                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자")
//                                )
//                        )
//                ));
//
//    }
//
//    @Test
//    void patchComment() throws Exception {
//        //given
//        LocalDateTime time = LocalDateTime.now();
//        long commentId = 1L;
//
//        CommentPatchDto patchDto = new CommentPatchDto();
//        patchDto.setBody("댓글2");
//
//        String content = gson.toJson(patchDto);
//
//        CommentResponseDto responseDto = new CommentResponseDto(1L, "댓글2", "홍길동1", time, LocalDateTime.now(), 1L);
//
//        given(mapper.commentPatchDtoToComment(Mockito.any(CommentPatchDto.class)))
//                .willReturn(new Comment());
//
//        given(commentService.updateComment(Mockito.any(Comment.class)))
//                .willReturn(new Comment());
//
//        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class)))
//                .willReturn(responseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                patch("/v1/comments/{comment-id}", commentId)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//        );
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.commentId").value(commentId))
//                .andExpect(jsonPath("$.data.body").value(patchDto.getBody()))
//                .andDo(document("patch-comment",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(parameterWithName("comment-id").description("댓글 식별자")),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 식별자").ignored(),
//                                        fieldWithPath("body").type(JsonFieldType.STRING).description("댓글 내용").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("댓글 내용"),
//                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("댓글 작성 회원 이름"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("댓글 생성 날짜"),
//                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 날짜"),
//                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자")
//                                )
//                        ))
//                );
//    }
//
//    @Test
//    void getComment() throws Exception {
//        //given
//        long commentId = 1L;
//
//        CommentResponseDto responseDto = new CommentResponseDto(1L, "댓글2", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L);
//
//        given(commentService.findComment(Mockito.anyLong()))
//                .willReturn(new Comment());
//
//        given(mapper.commentToCommentResponseDto(Mockito.any(Comment.class)))
//                .willReturn(responseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                get("/v1/comments/{comment-id}", commentId)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.commentId").value(commentId))
//                .andDo(document("get-comment",
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(parameterWithName("comment-id").description("댓글 식별자")),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
//                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("댓글 내용"),
//                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("댓글 작성 회원 이름"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("댓글 생성 날짜"),
//                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 날짜"),
//                                        fieldWithPath("data.answerId").type(JsonFieldType.NUMBER).description("답변 식별자")
//                                )
//                        ))
//                );
//
//    }
//
//    @Test
//    void getComments() throws Exception {
//        //given
//        List<CommentResponseDto> responseDtos = new ArrayList<>(List.of(
//                new CommentResponseDto(1L, "댓글1", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L),
//                new CommentResponseDto(2L, "댓글2", "홍길동1", LocalDateTime.now(), LocalDateTime.now(), 1L),
//                new CommentResponseDto(3L, "댓글3", "홍길동3", LocalDateTime.now(), LocalDateTime.now(), 2L)
//        ));
//
//        given(commentService.findComments())
//                .willReturn(new ArrayList<>());
//
//        given(mapper.commentsToCommentResponseDtos(Mockito.anyList()))
//                .willReturn(responseDtos);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                get("/v1/comments")
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray())
//                .andDo(document("get-comments",
//                        preprocessResponse(prettyPrint()),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
//                                        fieldWithPath("data[].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
//                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("댓글 내용"),
//                                        fieldWithPath("data[].displayName").type(JsonFieldType.STRING).description("댓글 작성 회원 이름"),
//                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("댓글 생성 날짜"),
//                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("댓글 수정 날짜"),
//                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자")
//                                )
//                        ))
//                );
//    }
//
//    @Test
//    void deleteComment() throws Exception {
//        //given
//        long commentId = 1L;
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                delete("/v1/comments/{comment-id}", commentId)
//        );
//
//        //then
//        actions
//                .andExpect(status().isNoContent())
//                .andDo(document("delete-comment",
//                        pathParameters(parameterWithName("comment-id").description("댓글 식별자"))
//                        )
//                );
//
//    }
//}
