package com.codestates.preproject.domain.question.entity;

import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.domain.hashtag.entity.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class QuestionHashtag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionHashtagId;

    @ManyToOne
    @JoinColumn(name = "HASHTAG_ID")
    private Hashtag hashtag;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
}
