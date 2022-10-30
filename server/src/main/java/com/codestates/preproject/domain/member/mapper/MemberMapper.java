package com.codestates.preproject.domain.member.mapper;

import com.codestates.preproject.domain.member.dto.MemberPostDto;
import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.dto.MemberPatchDto;
import com.codestates.preproject.domain.member.dto.MemberResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);

    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);

    MemberResponseDto memberToMemberResponseDto(Member member);

    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
