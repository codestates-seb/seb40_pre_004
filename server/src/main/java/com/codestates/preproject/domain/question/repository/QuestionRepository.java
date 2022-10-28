package com.codestates.preproject.domain.question.repository;

import com.codestates.preproject.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
