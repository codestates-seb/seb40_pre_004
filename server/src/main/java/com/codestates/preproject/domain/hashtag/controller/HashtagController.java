package com.codestates.preproject.domain.hashtag.controller;

import com.codestates.preproject.domain.hashtag.dto.HashtagPatchDto;
import com.codestates.preproject.domain.hashtag.dto.HashtagPostDto;
import com.codestates.preproject.domain.hashtag.dto.HashtagResponseDto;
import com.codestates.preproject.domain.hashtag.entity.Hashtag;
import com.codestates.preproject.domain.hashtag.mapper.HashtagMapper;
import com.codestates.preproject.domain.hashtag.service.HashtagService;
import com.codestates.preproject.page.PageInfo;
import com.codestates.preproject.page.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/hashtags")
@Validated
public class HashtagController {
    private final HashtagService hashtagService;
    private final HashtagMapper mapper;

    public HashtagController(HashtagService hashtagService, HashtagMapper mapper) {
        this.hashtagService = hashtagService;
        this.mapper = mapper;
    }

/*
        HashtagController 전체 흐름 구현(dto 클래스, 비즈니스 계층 미구현)
*/

    @PostMapping
    public ResponseEntity postHashtag(@Valid @RequestBody HashtagPostDto postDto) {

        Hashtag hashtag = mapper.hashtagPostDtoToHashtag(postDto);
        Hashtag createdHashtag = hashtagService.createHashtag(hashtag);
        HashtagResponseDto responseDto = mapper.hashtagToHashtagResponseDto(createdHashtag);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{hashtag-id}")
    public ResponseEntity patchHashtag(@PathVariable("hashtag-id") @Positive long hashtagId,
                                       @Valid @RequestBody HashtagPatchDto patchDto) {
        patchDto.setHashtagId(hashtagId);

        Hashtag hashtag = mapper.hashtagPatchDtoToHashtag(patchDto);
        Hashtag updatedHashtag = hashtagService.updateHashtag(hashtag);
        HashtagResponseDto responseDto = mapper.hashtagToHashtagResponseDto(updatedHashtag);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{hashtag-id}")
    public ResponseEntity getHashtag(@PathVariable("hashtag-id") @Positive long hashtagId) {

        Hashtag foundHashtag = hashtagService.findHashtag(hashtagId);
        HashtagResponseDto responseDto = mapper.hashtagToHashtagResponseDto(foundHashtag);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getHashtags(@RequestParam("page") @Positive int page,
                                      @RequestParam("size") @Positive int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("hashtagId").descending());

        Page<Hashtag> hashtagPage = hashtagService.findHashtags(pageRequest);
        List<HashtagResponseDto> responseDtos = mapper.hashtagsToHashtagResponseDtos(hashtagPage.getContent());
        PageInfo pageInfo = new PageInfo(hashtagPage.getNumber() + 1, hashtagPage.getSize(), hashtagPage.getTotalElements(), hashtagPage.getTotalPages());
        PageResponseDto pageResponseDto = new PageResponseDto<>(responseDtos, pageInfo);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{hashtag-id}")
    public ResponseEntity deleteHashtag(@PathVariable("hashtag-id") @Positive long hashtagId) {

        hashtagService.deleteHashtag(hashtagId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
