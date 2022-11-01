package com.codestates.preproject.domain.answer.service;

import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.answer.repository.AnswerRepository;
import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.service.MemberService;
import com.codestates.preproject.domain.question.entity.Question;
import com.codestates.preproject.domain.question.service.QuestionService;
import com.codestates.preproject.exception.BusinessLogicException;
import com.codestates.preproject.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final MemberService memberService;

    public AnswerService(AnswerRepository answerRepository, QuestionService questionService, MemberService memberService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
        this.memberService = memberService;
    }

    public Answer createAnswer(Answer answer) {
        Question question = verifiedExistsQuestion(answer.getQuestion());
        Member member = verifiedExistsMember(answer.getMember());

        answer.setQuestion(question);
        answer.setMember(member);

        question.addAnswers(answer);
        member.addAnswer(answer);

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getBody())
                .ifPresent(body -> findAnswer.setBody(body));
        Optional.ofNullable(answer.getAnswerCheck())
                .ifPresent(answerCheck -> findAnswer.setAnswerCheck(answerCheck));

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    public List<Answer> findAnswers() {
        return answerRepository.findAll();
    }

    public void deleteAnswer(long answerId) {
        answerRepository.deleteById(answerId);
    }

    public Answer findVerifiedAnswer(long answerId){
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return answer;
    }

    private Question verifiedExistsQuestion(Question question){
        return questionService.findVerifiedQuestion(question.getQuestionId());
    }

    private Member verifiedExistsMember(Member member) {
        return memberService.findVerifiedMember(member.getMemberId());
    }
}
