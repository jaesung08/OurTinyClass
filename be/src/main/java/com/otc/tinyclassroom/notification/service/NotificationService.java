package com.otc.tinyclassroom.notification.service;

import com.otc.tinyclassroom.notification.dto.NotificationDto;
import com.otc.tinyclassroom.notification.entity.Notification;
import com.otc.tinyclassroom.notification.repository.NotificationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 알림 Service.
 */
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationByReceiver(Long receiver) {
        return null;
    }

    /**
     * 알림 읽음처리.
     *
     * @param memberId 알림 받는 사람의 아이디 (String)
     */
    @Transactional
    public ArrayList<NotificationDto> retrieveUnsentNotifications(String memberId) {
        List<Notification> unsentNotification = notificationRepository.findByReceiver_MemberIdAndSentFalse(memberId);
        // 알림 보냄처리
        unsentNotification.forEach(notification -> notification.setSent(true));
        return unsentNotification.stream().map(NotificationDto::from).collect(Collectors.toCollection(ArrayList::new));
    }
}
