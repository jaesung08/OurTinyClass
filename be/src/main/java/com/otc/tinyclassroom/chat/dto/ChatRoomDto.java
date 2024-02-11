package com.otc.tinyclassroom.chat.dto;

import com.otc.tinyclassroom.chat.entity.ChatRoom;

/**
 * 채팅룸 Dto.
 */
public record ChatRoomDto() {

    public static ChatRoomDto of() {
        return new ChatRoomDto();
    }

    /**
     * entity -> dto.
     */
    public static ChatRoomDto from(ChatRoom entity) {
        return new ChatRoomDto();
    }

    /**
     * dto -> entity.
     */
    public static ChatRoom toEntity(ChatRoomDto dto) {
        return ChatRoom.of();
    }
}
