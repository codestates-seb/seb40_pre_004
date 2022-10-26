package com.codestates.preproject.domain.answer.service;

import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.answer.repository.AnswerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        return null;
    }

    public Answer updateAnswer(Answer answer) {
        return null;
    }

    public Answer findAnswer(long answerId) {
        return null;
    }

    public Page<Answer> findAnswers(Pageable pageable) {
        return null;
    }

    public void deleteAnswer(long answerId) {

    }
}
