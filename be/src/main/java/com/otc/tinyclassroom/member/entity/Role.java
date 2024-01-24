package com.otc.tinyclassroom.member.entity;

import lombok.Getter;

/**
 * 사용자 ROLE 을 정의한 Enum 타입.
 */
@Getter
public enum Role {
    ROLE_STUDENT(1), ROLE_TEACHER(2), ROLE_MENTOR(3), ROLE_ADMIN(4);

    private final int value;

    Role(int value) {
        this.value = value;
    }
}
