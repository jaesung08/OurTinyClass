package com.otc.tinyclassroom.board.dto.response;

import com.otc.tinyclassroom.board.dto.ArticleCommentDto;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ArticleCommentReponse DTO for {@link com.otc.tinyclassroom.board.entity.ArticleComment}.
 */
public record ArticleCommentResponseDto(Long id, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) implements Serializable {

    public static ArticleCommentResponseDto of(Long id, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ArticleCommentResponseDto(id, content, name, createdAt, modifiedAt);
    }

    public static ArticleCommentResponseDto from(ArticleCommentDto dto) {
        return new ArticleCommentResponseDto(
            dto.id(),
            dto.content(),
            dto.member().name(),
            dto.createdAt(),
            dto.modifiedAt()
        );
    }

}