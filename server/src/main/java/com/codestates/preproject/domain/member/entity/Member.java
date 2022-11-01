package com.codestates.preproject.domain.member.entity;

import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.domain.comment.entity.Comment;
import com.codestates.preproject.domain.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false, unique = true)
    private String displayName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean optIn;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>(); // 사용자 권한 등록 테이블

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
