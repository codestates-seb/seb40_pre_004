package com.codestates.preproject.domain.member;

import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class DBMemberService implements MemberService {

    private final MemberRepository memberRepository;
    public final PasswordEncoder passwordEncoder;

    public DBMemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
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

    public void verifyExistsEmail(String email) {

        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isPresent()) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }
    }



}
