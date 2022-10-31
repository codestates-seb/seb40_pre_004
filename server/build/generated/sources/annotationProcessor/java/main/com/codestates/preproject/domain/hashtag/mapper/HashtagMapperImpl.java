package com.codestates.preproject.domain.hashtag.mapper;

import com.codestates.preproject.domain.hashtag.dto.HashtagPatchDto;
import com.codestates.preproject.domain.hashtag.dto.HashtagPostDto;
import com.codestates.preproject.domain.hashtag.dto.HashtagResponseDto;
import com.codestates.preproject.domain.hashtag.entity.Hashtag;
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
public class HashtagMapperImpl implements HashtagMapper {

    @Override
    public Hashtag hashtagPostDtoToHashtag(HashtagPostDto hashtagPostDto) {
        if ( hashtagPostDto == null ) {
            return null;
        }

        Hashtag hashtag = new Hashtag();

        return hashtag;
    }

    @Override
    public Hashtag hashtagPatchDtoToHashtag(HashtagPatchDto hashtagPatchDto) {
        if ( hashtagPatchDto == null ) {
            return null;
        }

        Hashtag hashtag = new Hashtag();

        hashtag.setHashtagId( hashtagPatchDto.getHashtagId() );

        return hashtag;
    }

    @Override
    public HashtagResponseDto hashtagToHashtagResponseDto(Hashtag hashtag) {
        if ( hashtag == null ) {
            return null;
        }

        HashtagResponseDto hashtagResponseDto = new HashtagResponseDto();

        return hashtagResponseDto;
    }

    @Override
    public List<HashtagResponseDto> hashtagsToHashtagResponseDtos(List<Hashtag> hashtags) {
        if ( hashtags == null ) {
            return null;
        }

        List<HashtagResponseDto> list = new ArrayList<HashtagResponseDto>( hashtags.size() );
        for ( Hashtag hashtag : hashtags ) {
            list.add( hashtagToHashtagResponseDto( hashtag ) );
        }

        return list;
    }
}
