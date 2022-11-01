package com.codestates.preproject.domain.comment.mapper;

import com.codestates.preproject.domain.comment.dto.CommentPatchDto;
import com.codestates.preproject.domain.comment.dto.CommentPostDto;
import com.codestates.preproject.domain.comment.dto.CommentResponseDto;
import com.codestates.preproject.domain.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T11:17:40+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.16.1 (Azul Systems, Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentPostDtoToComment(CommentPostDto commentPostDto) {
        if ( commentPostDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setMember( commentPostDto.getMember() );
        comment.setAnswer( commentPostDto.getAnswer() );
        comment.setBody( commentPostDto.getBody() );

        return comment;
    }

    @Override
    public Comment commentPatchDtoToComment(CommentPatchDto commentPatchDto) {
        if ( commentPatchDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentId( commentPatchDto.getCommentId() );
        comment.setBody( commentPatchDto.getBody() );

        return comment;
    }

    @Override
    public CommentResponseDto commentToCommentResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDto commentResponseDto = new CommentResponseDto();

        commentResponseDto.setCommentId( comment.getCommentId() );
        commentResponseDto.setBody( comment.getBody() );
        commentResponseDto.setDisplayName( comment.getDisplayName() );
        commentResponseDto.setCreatedAt( comment.getCreatedAt() );
        commentResponseDto.setModifiedAt( comment.getModifiedAt() );
        commentResponseDto.setAnswerId( comment.getAnswerId() );

        return commentResponseDto;
    }

    @Override
    public List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentResponseDto> list = new ArrayList<CommentResponseDto>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToCommentResponseDto( comment ) );
        }

        return list;
    }
}
