package com.otc.tinyclassroom.notification.service;

import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import com.otc.tinyclassroom.notification.dto.NotificationDto;
import com.otc.tinyclassroom.notification.entity.Notification;
import com.otc.tinyclassroom.notification.repository.NotificationRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 실시간 알림 Service.
 */
@Service
@RequiredArgsConstructor
public class NotificationEmitterService {

    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    /**
     * 회원에 대한 Emitter를 생성.
     *
     * @param memberId 회원 아이디
     * @return ServerSentEmitter
     */
    public SseEmitter createEmitter(String memberId) {
        SseEmitter emitter = new SseEmitter(180_000L);
        this.emitters.put(memberId, emitter);
        emitter.onCompletion(() -> this.emitters.remove(memberId));
        emitter.onTimeout(() -> this.emitters.remove(memberId));
        emitter.onError(e -> this.emitters.remove(memberId));
        return emitter;
    }

    /**
     * 알림을 보내거나 저장.
     *
     * @param dto 알림
     */
    public void sendNotificationOrSave(NotificationDto dto) {
        String memberId = dto.receiver().memberId();
        SseEmitter emitter = this.emitters.get(memberId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("notification").data(dto));
                saveNotification(dto, true);
            } catch (IOException e) {
                saveNotification(dto, false);
            }
        } else {
            saveNotification(dto, false);
        }
    }

    /**
     * 알림을 저장한다.
     *
     * @param dto    알림
     * @param isSent 저장 상태
     */
    public void saveNotification(NotificationDto dto, boolean isSent) {
        Notification notification = Notification.from(dto);
        notification.setSent(isSent);
        notificationRepository.save(notification);
    }


    /**
     * 연결된 모두에게 알림 보내기.
     *
     * @param dto 알림내용
     */
    public void sentToAll(NotificationDto dto) {
        this.emitters.forEach((memberId, emitter) -> {
            try {
                emitter.send(SseEmitter.event().name("notification").data(dto));
            } catch (IOException e) {
                this.emitters.remove(memberId);
            }
        });
    }


    /**
     * 특정 반에게 알림 보내기.
     *
     * @param classRoomId 반 아이디
     * @param data        알림 메세지
     */
    public void sendToSpecificClassRoom(Long classRoomId, Object data) {
        List<Member> members = memberRepository.findByClassRoom_Id(classRoomId);
        members.forEach(member -> {
            SseEmitter emitter = this.emitters.get(member.getMemberId());
            if (emitter != null) {
                try {
                    emitter.send(SseEmitter.event().name("notification").data(data));
                } catch (IOException e) {
                    this.emitters.remove(member.getMemberId());
                }
            }
        });
    }
}
