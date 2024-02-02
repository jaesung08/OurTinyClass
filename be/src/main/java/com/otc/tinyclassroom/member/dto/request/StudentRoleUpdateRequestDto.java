package com.otc.tinyclassroom.member.dto.request;

import com.otc.tinyclassroom.member.entity.StudentRoleUpdateRequest;

/**
 * Role 변경 요청 DTO.
 */
public record StudentRoleUpdateRequestDto(Long memberId, int beforeSchoolType, String quitReason, String beforeSchool) {
    public static StudentRoleUpdateRequestDto of(Long memberId, int beforeSchoolType, String quitReason, String beforeSchool) {
        return new StudentRoleUpdateRequestDto(memberId, beforeSchoolType, quitReason, beforeSchool);
    }

    /**
     * StudentRoleUpdateRequestDto 로 변환한다.
     */
    public static StudentRoleUpdateRequestDto from(StudentRoleUpdateRequest entity) {
        return new StudentRoleUpdateRequestDto(
            entity.getMember().getId(),
            entity.getBeforeSchoolType(),
            entity.getQuitReason(),
            entity.getBeforeSchool()
        );
    }
}
