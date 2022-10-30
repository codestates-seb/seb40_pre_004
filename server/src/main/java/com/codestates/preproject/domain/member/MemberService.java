package com.codestates.preproject.domain.member;

import com.codestates.preproject.domain.member.entity.Member;

public interface MemberService {
    Member createMember(Member member);
    public void verifyExistsEmail(String email);
}
