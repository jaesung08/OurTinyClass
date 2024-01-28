package com.otc.tinyclassroom.chat.repository;

import com.otc.tinyclassroom.chat.dto.ChatRoomDto;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoomDto> chatRoomDtoMap;

    @PostConstruct
    private void init() {
        chatRoomDtoMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRooms() {
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDto> result = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDto findRoomById(String id) {
        return chatRoomDtoMap.get(id);
    }

    public ChatRoomDto createChatRoomDto(String name) {
        ChatRoomDto room = ChatRoomDto.create(name);
        chatRoomDtoMap.put(room.getRoomId(), room);

        return room;
    }
}
