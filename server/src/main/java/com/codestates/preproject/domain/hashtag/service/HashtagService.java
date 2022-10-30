package com.codestates.preproject.domain.hashtag.service;

import com.codestates.preproject.domain.hashtag.entity.Hashtag;
import com.codestates.preproject.domain.hashtag.repository.HashtagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public Hashtag createHashtag(Hashtag hashtag) {
        return null;
    }

    public Hashtag updateHashtag(Hashtag hashtag) {
        return null;
    }

    public Hashtag findHashtag(long hashtagId) {
        return null;
    }

    public Page<Hashtag> findHashtags(Pageable pageable) {
        return null;
    }

    public void deleteHashtag(long hashtagId) {

    }
}
