package com.codestates.preproject.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberPostDto {
    private String displayName;
    private String email;
    private String password;
}
