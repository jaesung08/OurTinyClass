package com.otc.tinyclassroom.chat.repository;

import com.otc.tinyclassroom.chat.dto.response.ChatRoomResponseDto;
import java.util.List;

/**
 * Querydsl 을 사용하는 repository의 interface.
 */
public interface ChatRoomRepositoryCustom {

    List<ChatRoomResponseDto> findAllChatRoomByMemberId(Long userId);

}
