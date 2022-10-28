package com.codestates.preproject.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDto {
    private long memberId;
    private String displayName;
    private String email;
    private String password;
    private Boolean optIn;
}
