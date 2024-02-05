package com.otc.tinyclassroom.notification.dto;

import com.otc.tinyclassroom.member.dto.MemberDto;
import com.otc.tinyclassroom.notification.entity.Notification;
import com.otc.tinyclassroom.notification.entity.type.NotificationType;
import java.time.LocalDateTime;

/**
 * 알림 DTO. DTO for {@link com.otc.tinyclassroom.notification.entity.Notification}.
 */
public record NotificationDto(Long id, MemberDto receiver, String content, NotificationType notificationType, boolean sent, LocalDateTime createdAt) {

    /**
     * 알림 Entity를 변환하는 메소드.
     *
     * @param entity 알림 Entity
     * @return 알림 DTO
     */
    public static NotificationDto from(Notification entity) {
        return new NotificationDto(
            entity.getId(),
            MemberDto.from(entity.getReceiver()),
            entity.getContent(),
            entity.getNotificationType(),
            entity.isSent(),
            entity.getCreatedAt()
        );
    }
}