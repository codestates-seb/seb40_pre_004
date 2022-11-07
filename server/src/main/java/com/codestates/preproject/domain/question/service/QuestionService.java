package com.codestates.preproject.domain.question.service;

import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.domain.member.service.MemberService;
import com.codestates.preproject.domain.question.entity.Question;
import com.codestates.preproject.domain.question.repository.QuestionRepository;
import com.codestates.preproject.exception.BusinessLogicException;
import com.codestates.preproject.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    public QuestionService(QuestionRepository questionRepository, MemberService memberService) {
        this.questionRepository = questionRepository;
        this.memberService = memberService;
    }

    public Question createQuestion(Question question) {
        Member member = verifyExistingMember(question.getMember());
        question.setMember(member);

        member.addQuestion(question);

        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getBody())
                .ifPresent(body -> findQuestion.setBody(body));
        Optional.ofNullable(question.getTags())
                .ifPresent(tags -> {
                    if (!tags.equals(new ArrayList<>())) {
                        findQuestion.setTags(tags);
                    }
                });

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {

        Question question = findVerifiedQuestion(questionId);
        question.addViews();

        return question;
    }

    public Page<Question> findQuestions(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("questionId").descending());

        return questionRepository.findAll(pageRequest);
    }

    public void deleteQuestion(long questionId) {
        Question question = findVerifiedQuestion(questionId);
        questionRepository.delete(question);
    }

    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question question = optionalQuestion.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return question;
    }

    private Member verifyExistingMember(Member member) {

        return memberService.findVerifiedMember(member.getMemberId());
    }
}
