package com.codestates.preproject.domain.member.controller;

import com.codestates.preproject.domain.member.dto.MemberPatchDto;
import com.codestates.preproject.domain.member.dto.MemberPostDto;
import com.codestates.preproject.domain.member.dto.MemberResponseDto;
import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.mapper.MemberMapper;
import com.codestates.preproject.domain.member.service.MemberService;
import com.codestates.preproject.page.PageInfo;
import com.codestates.preproject.page.PageResponseDto;
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

@RestController
@RequestMapping("/v1/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

/*
    MemberController 전체 흐름 구현(dto 클래스, 비즈니스 계층 미구현)
*/

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto postDto) {

        Member member = mapper.memberPostDtoToMember(postDto);
        Member createdMember = memberService.createMember(member);
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(createdMember);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto patchDto) {
        patchDto.setMemberId(memberId);

        Member member = mapper.memberPatchDtoToMember(patchDto);
        Member updatedMember = memberService.updateMember(member);
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(updatedMember);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {

        Member foundMember = memberService.findMember(memberId);
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(foundMember);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@RequestParam("page") @Positive int page,
                                     @RequestParam("size") @Positive int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("memberId").descending());

        Page<Member> memberPage = memberService.findMembers(pageRequest);
        List<MemberResponseDto> responseDtos = mapper.membersToMemberResponseDtos(memberPage.getContent());
        PageInfo pageInfo = new PageInfo(memberPage.getNumber() + 1, memberPage.getSize(), memberPage.getTotalElements(), memberPage.getTotalPages());
        PageResponseDto pageResponseDto = new PageResponseDto<>(responseDtos, pageInfo);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
