package com.otc.tinyclassroom.community.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link com.otc.tinyclassroom.community.entity.Article}
 */
public record ArticleCreateRequestDto(Long classRoomId, String title, String content, String articleType) implements Serializable {

    public static ArticleCreateRequestDto of(Long classRoomId, String title, String content, String articleType) {
        return new ArticleCreateRequestDto(classRoomId,title, content, articleType);
    }
}