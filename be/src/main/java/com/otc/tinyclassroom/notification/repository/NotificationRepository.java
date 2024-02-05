package com.otc.tinyclassroom.notification.repository;

import com.otc.tinyclassroom.notification.entity.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 알림 Repository.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByReceiver_MemberIdAndSentFalse(String memberId);
}