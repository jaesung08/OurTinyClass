package com.otc.tinyclassroom.board.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link com.otc.tinyclassroom.board.entity.ArticleComment}.
 */
public record ArticleCommentRequestDto(Long articleId, String content) implements Serializable {

    public static ArticleCommentRequestDto of(Long articleId, String content) {
        return new ArticleCommentRequestDto(articleId, content);
    }

}