package com.otc.tinyclassroom.chat.dto;

import com.otc.tinyclassroom.chat.entity.ChatMessage;
import com.otc.tinyclassroom.member.dto.MemberDto;

/**
 * 메세지 Dto.
 */
public record ChatMessageDto(ChatRoomDto chatRoomDto, MemberDto memberDto, String message, Boolean isSystem) {

    public static ChatMessageDto of(ChatRoomDto chatRoomDto, MemberDto memberDto, String message, Boolean isSystem) {
        return new ChatMessageDto(chatRoomDto, memberDto, message, isSystem);
    }

    /**
     * entity -> dto.
     */
    public static ChatMessageDto from(ChatMessage entity) {
        return new ChatMessageDto(
                ChatRoomDto.from(entity.getChatRoom()),
                MemberDto.from(entity.getMember()),
                entity.getMessage(),
                entity.getIsSystem()
        );
    }

    /**
     * dto -> entity.
     */
    public static ChatMessage toEntity(ChatMessageDto dto) {
        return ChatMessage.of(
                ChatRoomDto.toEntity(dto.chatRoomDto),
                MemberDto.toEntity(dto.memberDto),
                dto.message,
                dto.isSystem
        );
    }
}
