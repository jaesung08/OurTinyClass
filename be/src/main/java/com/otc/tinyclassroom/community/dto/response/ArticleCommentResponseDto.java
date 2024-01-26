package com.otc.tinyclassroom.community.dto.response;

import com.otc.tinyclassroom.community.dto.ArticleCommentDto;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ArticleCommentReponse DTO for {@link com.otc.tinyclassroom.community.entity.ArticleComment}.
 */
public record ArticleCommentResponseDto(Long id, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) implements Serializable {

    public static ArticleCommentResponseDto of(Long id, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ArticleCommentResponseDto(id, content, name, createdAt, modifiedAt);
    }

    /**
     * ArticleCommentDto 로부터 ArticleCommentResponse 로 전환하는 메서드.
     */
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