package com.codestates.preproject.domain.member.service;

import com.codestates.preproject.auth.utils.CustomAuthorityUtils;
import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.repository.MemberRepository;
import com.codestates.preproject.exception.BusinessLogicException;
import com.codestates.preproject.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final CustomAuthorityUtils authorityUtils;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    public Member createMember(Member member) {

        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        if (optionalMember.isPresent()) {
            if (passwordEncoder.matches(member.getPassword(), optionalMember.get().getPassword())) {
                throw new BusinessLogicException(ExceptionCode.MEMBER_REDIRECTION_LOGIN_SUCCESS);
            } else {
                throw new BusinessLogicException(ExceptionCode.MEMBER_REDIRECTION_FIND_PASSWORD);
            }
        }

        verifyExistingDisplayName(member.getDisplayName());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        Member savedMember = memberRepository.save(member);

        return savedMember;
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

    private void verifyExistingDisplayName(String displayName) {
        Optional<Member> optionalMember = memberRepository.findByDisplayName(displayName);

        if (optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_DISPLAY_NAME_EXISTS);
        }
    }
}
