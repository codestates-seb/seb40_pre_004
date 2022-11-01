package com.codestates.preproject.config;

import com.codestates.preproject.domain.member.repository.MemberRepository;
import com.codestates.preproject.domain.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JavaConfiguration {

    @Bean
    public MemberService dbMemberService(MemberRepository memberRepository,
                                         PasswordEncoder passwordEncoder) {
        return new MemberService(memberRepository, passwordEncoder);
    }
}
