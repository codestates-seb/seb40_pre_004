package com.codestates.preproject.domain.answer.service;

import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.answer.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        verifiedAnswer(answer.getAnswerId());

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findByAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getBody())
                .ifPresent(body -> findAnswer.setBody(body));

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(long answerId) {
        return findByAnswer(answerId);
    }

    public List<Answer> findAnswers() {
        return answerRepository.findAll();
    }

    public void deleteAnswer(long answerId) {
        answerRepository.deleteById(answerId);
    }

    public Answer findByAnswer(long answerId){
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("질문이 등록되지 않았습니다."));
        return answer;
    }

    private void verifiedAnswer(long answerId){
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        if(optionalAnswer.isPresent()){
            throw new RuntimeException("중복된 질문입니다.");
        }
    }
}
