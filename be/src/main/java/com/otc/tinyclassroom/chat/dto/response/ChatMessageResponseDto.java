package com.otc.tinyclassroom.chat.dto.response;

import java.time.LocalDateTime;

/**
 * 채팅 메세지의 response dto.
 */
public record ChatMessageResponseDto(Long chatId, String roomId, String memberId, String message, LocalDateTime createdAt) {

    public static ChatMessageResponseDto of(Long chatId, String roomId, String memberId, String message, LocalDateTime createdAt) {
        return new ChatMessageResponseDto(chatId, roomId, memberId, message, createdAt);
    }
}
