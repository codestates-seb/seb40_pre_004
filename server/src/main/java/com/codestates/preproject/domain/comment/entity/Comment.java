package com.codestates.preproject.domain.comment.entity;

import com.codestates.preproject.domain.answer.entity.Answer;
import com.codestates.preproject.audit.Auditable;
import com.codestates.preproject.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public String getDisplayName() {
        return member.getDisplayName();
    }

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Column(nullable = false, length = 10000)
    private String body;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
