package com.codestates.preproject.domain.member.controller;

import com.codestates.preproject.domain.member.dto.LoginDto;
import com.codestates.preproject.domain.member.dto.MemberPatchDto;
import com.codestates.preproject.domain.member.dto.MemberPostDto;
import com.codestates.preproject.domain.member.dto.MemberResponseDto;
import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.mapper.MemberMapper;
import com.codestates.preproject.domain.member.service.MemberService;
import com.codestates.preproject.dto.PageInfo;
import com.codestates.preproject.dto.MultiResponseDto;
import com.codestates.preproject.dto.SingleResponseDto;
import com.codestates.preproject.security.dto.TokenResponseDto;
import com.codestates.preproject.security.jwt.JwtProvider;
import com.codestates.preproject.security.jwt.MemberDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Validated
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto) throws JsonProcessingException {

        Member member = mapper.loginDtoToMember(loginDto);
        Member authorizedMember = memberService.loginMember(member);
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(authorizedMember);

        TokenResponseDto tokenResponseDto = jwtProvider.createTokensByLogin(responseDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + tokenResponseDto.getAtk());
        headers.add("Refresh", tokenResponseDto.getRtk());

        return new ResponseEntity<>(new SingleResponseDto<>("로그인 성공"), headers, HttpStatus.OK);
    }


    @GetMapping("/reissue")
    public ResponseEntity reissue(@AuthenticationPrincipal MemberDetails memberDetails) throws JsonProcessingException {
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(memberDetails.getMember());
        TokenResponseDto tokenResponseDto = jwtProvider.reissueAtk(responseDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + tokenResponseDto.getAtk());

        return new ResponseEntity<>(new SingleResponseDto<>("AccessToken 재발급 성공"), headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto postDto) {

        Member member = mapper.memberPostDtoToMember(postDto);
        Member createdMember = memberService.createMember(member);
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(createdMember);

        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto patchDto) {
        patchDto.setMemberId(memberId);

        Member member = mapper.memberPatchDtoToMember(patchDto);
        Member updatedMember = memberService.updateMember(member);
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(updatedMember);

        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {

        Member foundMember = memberService.findMember(memberId);
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(foundMember);

        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@RequestParam("page") @Positive int page,
                                     @RequestParam("size") @Positive int size) {

        Page<Member> memberPage = memberService.findMembers(page, size);
        List<MemberResponseDto> responseDtos = mapper.membersToMemberResponseDtos(memberPage.getContent());
        PageInfo pageInfo = new PageInfo(memberPage.getNumber() + 1, memberPage.getSize(), memberPage.getTotalElements(), memberPage.getTotalPages());

        return new ResponseEntity<>(new MultiResponseDto<>(responseDtos, pageInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
