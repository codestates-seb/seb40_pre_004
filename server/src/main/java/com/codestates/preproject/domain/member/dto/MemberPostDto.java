package com.codestates.preproject.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class MemberPostDto {
    private String displayName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Boolean optIn;
}
