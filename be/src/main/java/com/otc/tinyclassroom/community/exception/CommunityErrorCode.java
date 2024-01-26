package com.otc.tinyclassroom.community.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 커뮤니티 에러코드.
 */
@Getter
@AllArgsConstructor
public enum CommunityErrorCode {
    // 공통
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저가 없습니다."),
    CLASS_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 반이 없습니다."),
    // 게시글
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물이 없습니다."),
    // 댓글
    ARTICLE_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글 없습니다."),
    // 권한없음
    HAVE_NO_AUTHORITY(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
