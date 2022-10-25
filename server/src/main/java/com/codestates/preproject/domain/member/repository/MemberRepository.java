package com.codestates.preproject.domain.member.repository;

import com.codestates.preproject.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
