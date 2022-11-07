package com.codestates.preproject.domain.question.entity;

import com.codestates.preproject.converter.StringListConverter;
import com.codestates.preproject.domain.answer.dto.AnswerResponseDto;
import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.domain.comment.entity.Comment;
import com.codestates.preproject.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false, length = 10000)
    private String body;

    @Convert(converter = StringListConverter.class)
    @Column(nullable = false)
    private List<String> tags = new ArrayList<>();

    @Column(nullable = false)
    private long views = 0L;

    public void addViews() {
        views++;
    }

    @ManyToOne()
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public long getMemberId() {
        return member.getMemberId();
    }

    public String getDisplayName() {
        return member.getDisplayName();
    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public void addAnswers(Answer answer) {
        answers.add(answer);
    }

    public long getAnswerCount() {
        return answers.stream().count();
    }
}
