package com.otc.tinyclassroom.community.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link com.otc.tinyclassroom.community.entity.ArticleComment}.
 */
public record ArticleCommentUpdateRequestDto(String content) implements Serializable {

    public static ArticleCommentUpdateRequestDto of(String content) {
        return new ArticleCommentUpdateRequestDto(content);
    }

}