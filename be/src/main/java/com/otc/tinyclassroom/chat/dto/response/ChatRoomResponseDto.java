package com.otc.tinyclassroom.chat.dto.response;

/**
 * ChatRoomDto 리스트를 담는 request dto.
 */
public record ChatRoomResponseDto(String roomId, Long lastChatId, String lastMessage) {

    public static ChatRoomResponseDto of(String roomId, Long lastChatId, String lastMessage) {
        return new ChatRoomResponseDto(roomId, lastChatId, lastMessage);
    }
}
