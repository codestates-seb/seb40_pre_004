package com.codestates.preproject.config;

import com.codestates.preproject.domain.member.DBMemberService;
import com.codestates.preproject.domain.member.MemberService;
import com.codestates.preproject.domain.member.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JavaConfiguration {

    @Bean
    public MemberService dbMemberService(MemberRepository memberRepository,
                                         PasswordEncoder passwordEncoder) {
        return new DBMemberService(memberRepository, passwordEncoder);
    }
}
