package com.otc.tinyclassroom.notification.controller;

import com.otc.tinyclassroom.global.security.auth.PrincipalDetails;
import com.otc.tinyclassroom.notification.dto.NotificationDto;
import com.otc.tinyclassroom.notification.service.NotificationEmitterService;
import com.otc.tinyclassroom.notification.service.NotificationService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 알림 Controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationEmitterService notificationEmitterService;
    private final NotificationService notificationService;

    /**
     * 로그인한 유저와 SSE Connection.
     *
     * @param principal 인증정보
     * @return sseEmitter
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamNotification(@AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println(principal);
        System.out.println(principal.getMember());
        System.out.println(principal.getMember().getMemberId());
        SseEmitter emitter = notificationEmitterService.createEmitter(principal.getMember().getMemberId());
        List<NotificationDto> unsentNotifications = notificationService.retrieveUnsentNotifications(principal.getMember().getMemberId());
        unsentNotifications.forEach(notificationDto -> {
            try {
                emitter.send(SseEmitter.event().name("notification").data(notificationDto));
                notificationEmitterService.saveNotification(notificationDto, true);
            } catch (IOException e) {
                notificationEmitterService.saveNotification(notificationDto, false);
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
