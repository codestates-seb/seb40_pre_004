package com.codestates.preproject.domain.question.mapper;

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
    date = "2022-10-26T14:47:59+0900",
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

        return question;
    }

    @Override
    public Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatchDto.getQuestionId() );

        return question;
    }

    @Override
    public QuestionResponseDto questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        return questionResponseDto;
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
}
