package com.codestates.preproject.domain.member.controller;

import com.codestates.preproject.domain.member.controller.MemberController;
import com.codestates.preproject.domain.member.dto.MemberPostDto;
import com.codestates.preproject.domain.member.dto.MemberResponseDto;
import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.mapper.MemberMapper;
import com.codestates.preproject.domain.member.service.MemberService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@ActiveProfiles("local")
class MemberControllerTest {
    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void postMember() throws Exception {
        //given
        MemberPostDto postDto = new MemberPostDto("홍길동", "hgd@gmail.com", "hgd1234#");
        String content = gson.toJson(postDto);

        MemberResponseDto responseDto = new MemberResponseDto(1L, "홍길동", "hgd@gmail.com", "hgd1234#");

        given(mapper.memberPostDtoToMember(Mockito.any(MemberPostDto.class)))
                .willReturn(new Member());

        given(memberService.createMember(Mockito.any(Member.class)))
                .willReturn(new Member());

        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/v1/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.displayName").value(postDto.getDisplayName()))
                .andExpect(jsonPath("$.data.email").value(postDto.getEmail()))
                .andExpect(jsonPath("$.data.password").value(postDto.getPassword()))
                .andDo(document("post-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("displayName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("data.password").type(JsonFieldType.STRING).description("회원 비밀번호")
                                )
                        )
                ));
    }

    @Test
    void patchMember() {
    }

    @Test
    void getMember() {
    }

    @Test
    void getMembers() {
    }

    @Test
    void deleteMember() {
    }
}