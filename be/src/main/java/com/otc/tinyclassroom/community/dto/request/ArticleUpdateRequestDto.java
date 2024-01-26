package com.otc.tinyclassroom.community.dto.request;

import java.io.Serializable;

/**
 * 게시글 업데이트 requestDto.
 */
public record ArticleUpdateRequestDto(String title, String content) implements Serializable {

    public static ArticleUpdateRequestDto of(String title, String content) {
        return new ArticleUpdateRequestDto(title, content);
    }
}