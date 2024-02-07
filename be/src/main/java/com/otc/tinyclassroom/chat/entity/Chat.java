package com.otc.tinyclassroom.chat.entity;

import com.otc.tinyclassroom.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.CurrentTimestamp;

/**
 * 채팅 메세지 엔티티.
 */
@Getter
@Entity
@Table
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChatRoom chatRoom;

    @ManyToOne
    private Member member;

    private String message;

    @CurrentTimestamp
    private LocalDateTime createdAt;

    private Boolean isSystem;

    protected Chat() {

    }

    private Chat(Long id, ChatRoom chatRoom, Member member, String message, Boolean isSystem) {
        this.id = id;
        this.chatRoom = chatRoom;
        this.member = member;
        this.message = message;
        this.isSystem = isSystem;
    }

    /**
     * 팩토리 메서드 생성자.
     */
    public static Chat of(Long id, ChatRoom chatRoom, Member member, String message, Boolean isSystem) {
        return new Chat(id, chatRoom, member, message, isSystem);
    }
}
