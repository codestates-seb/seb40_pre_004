package com.codestates.preproject.domain.member.controller;

import com.codestates.preproject.domain.member.controller.MemberController;
import com.codestates.preproject.domain.member.dto.MemberPatchDto;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
        MemberPostDto postDto = new MemberPostDto("홍길동", "hgd@gmail.com", "hgd1234#", false);
        String content = gson.toJson(postDto);

        MemberResponseDto responseDto = new MemberResponseDto(1L, "홍길동", "hgd@gmail.com", "hgd1234#", false);

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
                .andExpect(jsonPath("$.data.optIn").value(postDto.getOptIn()))
                .andDo(document("post-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("displayName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호"),
                                        fieldWithPath("optIn").type(JsonFieldType.BOOLEAN).description("회원 알림 여부")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("data.password").type(JsonFieldType.STRING).description("회원 비밀번호"),
                                        fieldWithPath("data.optIn").type(JsonFieldType.BOOLEAN).description("회원 알림 여부")
                                )
                        )
                ));
    }

    @Test
    void patchMember() throws Exception {
        //given
        long memberId = 1L;
        MemberPatchDto patchDto = new MemberPatchDto();
        patchDto.setPassword("1234#");
        patchDto.setDisplayName("김길동");
        patchDto.setOptIn(true);

        String content = gson.toJson(patchDto);

        MemberResponseDto responseDto = new MemberResponseDto(1L, "김길동", "hgd@gmail.com", "1234#", true);

        given(mapper.memberPatchDtoToMember(Mockito.any(MemberPatchDto.class)))
                .willReturn(new Member());

        given(memberService.updateMember(Mockito.any(Member.class)))
                .willReturn(new Member());

        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/v1/members/{member-id}", memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(memberId))
                .andExpect(jsonPath("$.data.displayName").value(patchDto.getDisplayName()))
                .andExpect(jsonPath("$.data.password").value(patchDto.getPassword()))
                .andExpect(jsonPath("$.data.optIn").value(patchDto.getOptIn()))
                .andDo(document("patch-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
                                        fieldWithPath("displayName").type(JsonFieldType.STRING).description("회원 이름").optional(),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호").optional(),
                                        fieldWithPath("optIn").type(JsonFieldType.BOOLEAN).description("회원 알림 여부")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("data.password").type(JsonFieldType.STRING).description("회원 비밀번호"),
                                        fieldWithPath("data.optIn").type(JsonFieldType.BOOLEAN).description("회원 알림 여부")
                                )
                        )
                ));
    }

    @Test
    void getMember() throws Exception {
        //given
        long memberId = 1;

        MemberResponseDto response = new MemberResponseDto(memberId, "홍길동", "hgd@gmail.com", "hgd1234#", false);

        given(memberService.findMember(Mockito.anyLong()))
                .willReturn(new Member());

        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class)))
                .willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/v1/members/{member-id}", memberId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(memberId))
                .andDo(document("get-member",
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("member-id").description("회원 식별자")),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.displayName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("data.password").type(JsonFieldType.STRING).description("회원 비밀번호"),
                                        fieldWithPath("data.optIn").type(JsonFieldType.BOOLEAN).description("회원 알림 여부")
                                )
                        )
                ));
    }

    @Test
    void getMembers() throws Exception {
        //given
        int page = 1;
        int size = 5;

        List<Member> members = List.of(new Member(), new Member(), new Member());

        given(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt()))
                .willReturn(new PageImpl<>(members, PageRequest.of(page-1, size, Sort.by("memberId").descending()), members.size()));

        given(mapper.membersToMemberResponseDtos(Mockito.anyList()))
                .willReturn(List.of(
                        new MemberResponseDto(3L, "홍길동3", "hgd3@gmail.com", "a3333#", false),
                        new MemberResponseDto(2L, "홍길동2", "hgd2@gmail.com", "a2222#", true),
                        new MemberResponseDto(1L, "홍길동1", "hgd1@gmail.com", "a1111#", false)
                        )
                );

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/v1/members/?page={page}&size={size}", page, size)
                                .accept(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-members",
                        preprocessResponse(prettyPrint()),
                        requestParameters(List.of(parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("페이지 크기"))),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].displayName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("data[].password").type(JsonFieldType.STRING).description("회원 비밀번호"),
                                        fieldWithPath("data[].optIn").type(JsonFieldType.BOOLEAN).description("회원 알림 여부"),

                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 갯수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
                                )
                        )
                ));
    }

    @Test
    void deleteMember() throws Exception {
        //given
        long memberId = 1;

        //when
        ResultActions actions =
                mockMvc.perform(
                        delete("/v1/members/{member-id}", memberId)
                );

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-member",
                        pathParameters(parameterWithName("member-id").description("회원 식별자"))
                ));
    }
}