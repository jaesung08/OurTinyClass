package com.otc.tinyclassroom.notification.entity;

import com.otc.tinyclassroom.community.entity.AuditingFields;
import com.otc.tinyclassroom.member.dto.MemberDto;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.notification.dto.NotificationDto;
import com.otc.tinyclassroom.notification.entity.type.NotificationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 알램 Entity.
 */
@Getter
@Entity
@Table(name = "notification")
public class Notification extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member receiver; //알람 수신자.
    @Setter
    @Column
    private String content; //알람 내용
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @Setter
    @Column
    private boolean sent; //알람을 보냈는지에 대한 여부

    protected Notification() {
    }

    private Notification(Member receiver, String content, NotificationType notificationType, boolean sent) {
        this.receiver = receiver;
        this.content = content;
        this.notificationType = notificationType;
        this.sent = sent;
    }

    public static Notification of(Member receiver, String content, NotificationType notificationType, boolean sent) {
        return new Notification(receiver, content, notificationType, sent);
    }

    /**
     * Dto를 Entity로 변환한다.
     *
     * @param dto 알림 DTO
     * @return 알림 Entity
     */
    public static Notification from(NotificationDto dto) {
        return new Notification(
            MemberDto.toEntity(dto.receiver()),
            dto.content(),
            dto.notificationType(),
            dto.sent()
        );
    }
}
