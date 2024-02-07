package com.otc.tinyclassroom.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;

/**
 * 채팅룸 엔티티.
 */
@Getter
@Entity
@Table
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "chat_room_id")
    private List<ChatRoomMember> chatRoomMember;

    protected ChatRoom() {

    }

    private ChatRoom(Long id, List<ChatRoomMember> chatRoomMember) {
        this.id = id;
        this.chatRoomMember = chatRoomMember;
    }

    public static ChatRoom of(Long id, List<ChatRoomMember> chatRoomMemberList) {
        return new ChatRoom(id, chatRoomMemberList);
    }
}