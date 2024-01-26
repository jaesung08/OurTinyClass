package com.otc.tinyclassroom.community.dto.response;

import com.otc.tinyclassroom.community.dto.ArticleDto;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for ArticleResponse.
 */
public record ArticleResponseDto(Long id, String title, String content, String name, int hit, LocalDateTime createdAt, LocalDateTime modifiedAt) implements Serializable {

    public static ArticleResponseDto of(Long id, String title, String content, String name, int hit, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ArticleResponseDto(id, title, content, name, hit, createdAt, modifiedAt);
    }

    /**
     * ArticleDto 로부터 ArticleResponseDto 로 전환.
     */
    public static ArticleResponseDto from(ArticleDto dto) {
        return new ArticleResponseDto(
            dto.id(),
            dto.title(),
            dto.content(),
            dto.member().name(),
            dto.hit(),
            dto.createdAt(),
            dto.modifiedAt()
        );
    }
}