package com.codestates.preproject.domain.member.repository;

import com.codestates.preproject.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByDisplayName(String displayName);

}
