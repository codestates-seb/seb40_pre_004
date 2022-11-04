package com.codestates.preproject.security.jwt;

import lombok.Getter;

@Getter
public class Subject {

    private final long memberId;

    private final String email;

    private final String displayName;

    private final String type;

    public Subject(long memberId, String email, String displayName, String type) {
        this.memberId = memberId;
        this.email = email;
        this.displayName = displayName;
        this.type = type;
    }

    public static Subject atk(long memberId, String email, String displayName) {
        return new Subject(memberId, email, displayName, "ATK");
    }

    public static Subject rtk(long memberId, String email, String displayName) {
        return new Subject(memberId, email, displayName, "RTK");
    }
}
