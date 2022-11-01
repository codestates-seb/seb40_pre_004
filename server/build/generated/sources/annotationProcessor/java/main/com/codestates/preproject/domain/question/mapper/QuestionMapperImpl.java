package com.codestates.preproject.domain.question.mapper;

import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.domain.comment.dto.CommentResponseDto;
import com.codestates.preproject.domain.question.dto.QuestionDetailsResponseDto;
import com.codestates.preproject.domain.question.dto.QuestionPatchDto;
import com.codestates.preproject.domain.question.dto.QuestionPostDto;
import com.codestates.preproject.domain.question.dto.QuestionResponseDto;
import com.codestates.preproject.domain.question.entity.Question;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T15:55:11+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.16.1 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostDtoToQuestion(QuestionPostDto questionPostDto) {
        if ( questionPostDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setTitle( questionPostDto.getTitle() );
        question.setBody( questionPostDto.getBody() );
        List<String> list = questionPostDto.getTags();
        if ( list != null ) {
            question.setTags( new ArrayList<String>( list ) );
        }
        question.setMember( questionPostDto.getMember() );

        return question;
    }

    @Override
    public Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatchDto.getQuestionId() );
        question.setTitle( questionPatchDto.getTitle() );
        question.setBody( questionPatchDto.getBody() );
        List<String> list = questionPatchDto.getTags();
        if ( list != null ) {
            question.setTags( new ArrayList<String>( list ) );
        }

        return question;
    }

    @Override
    public QuestionResponseDto questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        questionResponseDto.setQuestionId( question.getQuestionId() );
        questionResponseDto.setTitle( question.getTitle() );
        questionResponseDto.setBody( question.getBody() );
        questionResponseDto.setMemberId( question.getMemberId() );
        questionResponseDto.setDisplayName( question.getDisplayName() );
        List<String> list = question.getTags();
        if ( list != null ) {
            questionResponseDto.setTags( new ArrayList<String>( list ) );
        }
        questionResponseDto.setCreatedAt( question.getCreatedAt() );
        questionResponseDto.setModifiedAt( question.getModifiedAt() );

        return questionResponseDto;
    }

    @Override
    public QuestionDetailsResponseDto questionToQuestionDetailsResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDetailsResponseDto questionDetailsResponseDto = new QuestionDetailsResponseDto();

        questionDetailsResponseDto.setQuestionId( question.getQuestionId() );
        questionDetailsResponseDto.setTitle( question.getTitle() );
        questionDetailsResponseDto.setBody( question.getBody() );
        questionDetailsResponseDto.setMemberId( question.getMemberId() );
        questionDetailsResponseDto.setDisplayName( question.getDisplayName() );
        List<String> list = question.getTags();
        if ( list != null ) {
            questionDetailsResponseDto.setTags( new ArrayList<String>( list ) );
        }
        questionDetailsResponseDto.setCreatedAt( question.getCreatedAt() );
        questionDetailsResponseDto.setModifiedAt( question.getModifiedAt() );
        questionDetailsResponseDto.setAnswers( answerListToAnswerResponseDtoList( question.getAnswers() ) );

        return questionDetailsResponseDto;
    }

    @Override
    public List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionResponseDto> list = new ArrayList<QuestionResponseDto>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponseDto( question ) );
        }

        return list;
    }

    protected AnswerResponseDto answerToAnswerResponseDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerResponseDto answerResponseDto = new AnswerResponseDto();

        answerResponseDto.setAnswerId( answer.getAnswerId() );
        answerResponseDto.setBody( answer.getBody() );
        answerResponseDto.setAnswerCheck( answer.getAnswerCheck() );
        answerResponseDto.setMemberDisplayName( answer.getMemberDisplayName() );
        answerResponseDto.setMemberId( answer.getMemberId() );
        answerResponseDto.setCreatedAt( answer.getCreatedAt() );
        answerResponseDto.setModifiedAt( answer.getModifiedAt() );
        answerResponseDto.setQuestionId( answer.getQuestionId() );
        List<CommentResponseDto> list = answer.getComments();
        if ( list != null ) {
            answerResponseDto.setComments( new ArrayList<CommentResponseDto>( list ) );
        }

        return answerResponseDto;
    }

    protected List<AnswerResponseDto> answerListToAnswerResponseDtoList(List<Answer> list) {
        if ( list == null ) {
            return null;
        }

        List<AnswerResponseDto> list1 = new ArrayList<AnswerResponseDto>( list.size() );
        for ( Answer answer : list ) {
            list1.add( answerToAnswerResponseDto( answer ) );
        }

        return list1;
    }
}
