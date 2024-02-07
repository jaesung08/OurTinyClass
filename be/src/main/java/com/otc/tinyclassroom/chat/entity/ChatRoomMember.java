package com.otc.tinyclassroom.chat.entity;

import com.otc.tinyclassroom.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Chat과 Member의 매핑을 위한 테이블.
 */
@Getter
@Entity
@Table
public class ChatRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private ChatRoom chatRoom;

    protected ChatRoomMember() {

    }

    private ChatRoomMember(Long id, Member member, ChatRoom chatRoom) {
        this.id = id;
        this.member = member;
        this.chatRoom = chatRoom;
    }

    public static ChatRoomMember of(Long id, Member member, ChatRoom chatRoom) {
        return new ChatRoomMember(id, member, chatRoom);
    }
}
