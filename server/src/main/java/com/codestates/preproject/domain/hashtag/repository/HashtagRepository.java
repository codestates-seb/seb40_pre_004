package com.codestates.preproject.domain.hashtag.repository;

import com.codestates.preproject.domain.hashtag.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
