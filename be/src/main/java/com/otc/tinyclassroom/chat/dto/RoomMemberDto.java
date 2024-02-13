package com.otc.tinyclassroom.chat.dto;

/**
 * 채팅방에 속한 멤버 dto.
 */
public record RoomMemberDto(Long id, String memberId, String name, String profileImage) {

    public static RoomMemberDto of(Long id, String memberId, String name, String profileImage) {
        return new RoomMemberDto(id, memberId, name, profileImage);
    }
}
