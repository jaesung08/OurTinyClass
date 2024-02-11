package com.otc.tinyclassroom.chat.entity;

import com.otc.tinyclassroom.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import org.hibernate.annotations.CurrentTimestamp;

/**
 * 채팅 메세지 엔티티.
 */
@Getter
@Entity
@Table
public class ChatMessage {

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

    protected ChatMessage() {

    }

    private ChatMessage(ChatRoom chatRoom, Member member, String message, Boolean isSystem) {
        this.chatRoom = chatRoom;
        this.member = member;
        this.message = message;
        this.isSystem = isSystem;
    }

    /**
     * 팩토리 메서드 생성자.
     */
    public static ChatMessage of(ChatRoom chatRoom, Member member, String message, Boolean isSystem) {
        return new ChatMessage(chatRoom, member, message, isSystem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatMessage chatMessage)) {
            return false;
        }
        return id.equals(chatMessage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
