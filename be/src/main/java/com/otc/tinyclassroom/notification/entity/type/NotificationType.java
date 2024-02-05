package com.otc.tinyclassroom.notification.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 알림 Type.
 */
@Getter
@AllArgsConstructor
public enum NotificationType {
    NEW_COMMENT_ON_ARTICLE("게시글에 새로운 댓글이 달렸습니다."),
    UPCOMING_LECTURE("강의가 곧 시작됩니다.");
    private final String message;
}
