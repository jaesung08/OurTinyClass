package com.otc.tinyclassroom.member.dto.request;

/**
 * Role 변경 요청 DTO.
 */
public record StudentRoleUpdateRequestDto(Long memberId, int beforeSchoolType, String quitReason, String beforeSchool) {
    public static StudentRoleUpdateRequestDto of(Long memberId, int beforeSchoolType, String quitReason, String beforeSchool) {
        return new StudentRoleUpdateRequestDto(memberId, beforeSchoolType, quitReason, beforeSchool);
    }
}