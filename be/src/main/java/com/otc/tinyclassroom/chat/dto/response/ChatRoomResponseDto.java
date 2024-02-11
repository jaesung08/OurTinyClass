package com.otc.tinyclassroom.chat.dto.response;

/**
 * ChatRoomDto 리스트를 담는 request dto.
 */
public record ChatRoomResponseDto(String roomId) {

    public static ChatRoomResponseDto of(String roomId) {
        return new ChatRoomResponseDto(roomId);
    }
}
