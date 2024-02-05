package com.otc.tinyclassroom.member.entity;

import lombok.Getter;

/**
 * 사용자 ROLE 을 정의한 Enum.
 */
@Getter
public enum Role {
    ROLE_USER("USER"), ROLE_STUDENT("STUDENT"), ROLE_TEACHER("TEACHER"), ROLE_MENTOR("MENTOR"), ROLE_ADMIN("ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
