package com.codestates.preproject.domain.answer.repository;

import com.codestates.preproject.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
