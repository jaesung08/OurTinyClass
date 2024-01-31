package com.otc.tinyclassroom.member.dto.request;

import com.otc.tinyclassroom.member.entity.StudentRoleUpdateRequest;

/**
 * Role 변경 요청 Dto.
 */

//TODO: beforeSchoolName
public record StudentRoleUpdateRequestDto(Long memberId, String content, int beforeSchoolType, int quitReason) {
    public static StudentRoleUpdateRequestDto of(Long memberId,  String content, int beforeSchoolType, int quitReason) {
        return new StudentRoleUpdateRequestDto(memberId, content, beforeSchoolType, quitReason);
    }

    /**
     * StudentRoleUpdateRequestDto 로 변환하는 메소드.
     */
    public static StudentRoleUpdateRequestDto from(StudentRoleUpdateRequest entity) {
        return new StudentRoleUpdateRequestDto(
            entity.getMember().getId(),
            entity.getContent(),
            entity.getBeforeSchoolType(),
            entity.getQuitReason()
        );
    }
}
