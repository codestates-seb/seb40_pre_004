package com.codestates.preproject.domain.member.service;

import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        Member saveMember = memberRepository.save(member);

        System.out.println("# Create Member in DB");
        return saveMember;
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
        Member member = optionalMember.orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다"));

        return member;
    }

    private void verifyExistsEmail(String email) {

        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isPresent()) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }
    }
}
