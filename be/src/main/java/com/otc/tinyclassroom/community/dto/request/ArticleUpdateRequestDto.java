package com.otc.tinyclassroom.community.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link com.otc.tinyclassroom.community.entity.Article}
 */
public record ArticleUpdateRequestDto(String title, String content) implements Serializable {

    public static ArticleUpdateRequestDto of(String title, String content) {
        return new ArticleUpdateRequestDto(title, content);
    }
}