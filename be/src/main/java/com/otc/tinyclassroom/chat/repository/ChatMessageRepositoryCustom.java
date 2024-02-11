package com.otc.tinyclassroom.chat.repository;

import com.otc.tinyclassroom.chat.dto.response.ChatMessageResponseDto;
import java.util.List;

/**
 * QueryDsl을 사용하기 위한 repository interface.
 */
public interface ChatMessageRepositoryCustom {
    List<ChatMessageResponseDto> findAllChatByRoomId(String roomId);
}
