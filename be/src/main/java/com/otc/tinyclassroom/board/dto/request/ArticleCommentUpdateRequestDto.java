package com.otc.tinyclassroom.board.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link com.otc.tinyclassroom.board.entity.ArticleComment}.
 */
public record ArticleCommentUpdateRequestDto(String content) implements Serializable {

    public static ArticleCommentUpdateRequestDto of(String content) {
        return new ArticleCommentUpdateRequestDto(content);
    }

}