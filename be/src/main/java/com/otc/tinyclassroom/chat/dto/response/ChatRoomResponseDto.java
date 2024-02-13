package com.otc.tinyclassroom.chat.dto.response;

import com.otc.tinyclassroom.chat.dto.RoomMemberDto;
import java.util.List;

/**
 * ChatRoomDto 리스트를 담는 request dto.
 */
public record ChatRoomResponseDto(String roomId, List<RoomMemberDto> roomMemberList, Long lastChatId, String lastMessage) {

    public static ChatRoomResponseDto of(String roomId, List<RoomMemberDto> roomMemberList, Long lastChatId, String lastMessage) {
        return new ChatRoomResponseDto(roomId, roomMemberList, lastChatId, lastMessage);
    }
}
