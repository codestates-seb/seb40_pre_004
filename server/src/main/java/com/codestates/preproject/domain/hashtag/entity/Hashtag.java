package com.codestates.preproject.domain.hashtag.entity;

import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.domain.question.entity.QuestionHashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hashtag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hashtagId;

    @Column(nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "hashtag")
    private List<QuestionHashtag> questionHashtags = new ArrayList<>();
}
