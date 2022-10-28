package com.codestates.preproject.domain.hashtag.mapper;

import com.codestates.preproject.domain.hashtag.dto.HashtagPatchDto;
import com.codestates.preproject.domain.hashtag.dto.HashtagPostDto;
import com.codestates.preproject.domain.hashtag.dto.HashtagResponseDto;
import com.codestates.preproject.domain.hashtag.entity.Hashtag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    Hashtag hashtagPostDtoToHashtag(HashtagPostDto hashtagPostDto);

    Hashtag hashtagPatchDtoToHashtag(HashtagPatchDto hashtagPatchDto);

    HashtagResponseDto hashtagToHashtagResponseDto(Hashtag hashtag);

    List<HashtagResponseDto> hashtagsToHashtagResponseDtos(List<Hashtag> hashtags);
}
