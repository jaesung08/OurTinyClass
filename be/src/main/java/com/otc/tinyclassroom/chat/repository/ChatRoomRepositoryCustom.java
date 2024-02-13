package com.otc.tinyclassroom.chat.repository;

import com.otc.tinyclassroom.chat.dto.ChatRoomDto;
import com.otc.tinyclassroom.chat.dto.RoomMemberDto;
import java.util.List;

/**
 * Querydsl 을 사용하는 repository의 interface.
 */
public interface ChatRoomRepositoryCustom {

    List<ChatRoomDto> findAllChatRoomByMemberId(Long userId);

    List<RoomMemberDto> findAllRoomMemberByRoomId(String roomId);

}
