package com.codestates.preproject.domain.member.dto;

import com.codestates.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPatchDto {
    private long memberId;

    @NotSpace
    private String displayName;

    @NotSpace
    private String password;

}
