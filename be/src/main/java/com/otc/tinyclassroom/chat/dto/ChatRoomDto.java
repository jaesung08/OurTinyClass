package com.otc.tinyclassroom.chat.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions;

    public static ChatRoomDto create(String name) {
        ChatRoomDto room = new ChatRoomDto();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        room.sessions = new HashSet<>();

        return room;
    }
}
