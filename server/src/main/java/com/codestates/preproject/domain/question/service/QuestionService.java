package com.codestates.preproject.domain.question.service;

import com.codestates.preproject.domain.question.entity.Question;
import com.codestates.preproject.domain.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question) {
        return null;
    }

    public Question updateQuestion(Question question) {
        return null;
    }

    public Question findQuestion(long questionId) {
        return null;
    }

    public Page<Question> findQuestions(Pageable pageable) {
        return null;
    }

    public void deleteQuestion(long questionId) {

    }
}
