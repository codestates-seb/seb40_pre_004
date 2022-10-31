package com.codestates.preproject.domain.member.service;

import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.repository.MemberRepository;
import com.codestates.preproject.exception.BusinessLogicException;
import com.codestates.preproject.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {

        if (member.getDisplayName().isBlank())
            member.setDisplayName("User" + Long.sum(memberRepository.count(),1));

        verifyExistsDisplayName(member.getDisplayName());
        verifyExistsEmail(member.getEmail());

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {

        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getDisplayName())
                .ifPresent(displayName -> findMember.setDisplayName(displayName));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(password));
        Optional.ofNullable(member.getOptIn())
                .ifPresent(optIn -> findMember.setOptIn(optIn));

        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId) {

        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("memberId").descending());

        return memberRepository.findAll(pageRequest);
    }

    public void deleteMember(long memberId) {

        Member member = findVerifiedMember(memberId);

        memberRepository.delete(member);
    }

    public Member findVerifiedMember(long memberId) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return member;
    }

    private void verifyExistsDisplayName(String displayName) {

        Optional<Member> optionalMember = memberRepository.findByDisplayName(displayName);

        if (optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_DISPLAY_NAME_EXISTS);
        }
    }

    private void verifyExistsEmail(String email) {

        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
        }
    }
}
