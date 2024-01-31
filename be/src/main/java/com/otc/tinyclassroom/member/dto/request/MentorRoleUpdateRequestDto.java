package com.otc.tinyclassroom.member.dto.request;

/**
 * Role 변경 요청 Dto.
 */
public record MentorRoleUpdateRequestDto(Long memberId, String content, String belong) {
    public static MentorRoleUpdateRequestDto of(Long memberId, String content, String belong) {
        return new MentorRoleUpdateRequestDto(memberId, content, belong);
    }
}
