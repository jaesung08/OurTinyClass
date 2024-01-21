package com.otc.tinyclassroom.board.dto.response;

import com.otc.tinyclassroom.board.dto.ArticleDto;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.otc.tinyclassroom.board.entity.Article}
 */
public record ArticleResponseDto (Long id, String title, String content, String name, LocalDateTime createdAt) implements Serializable {

    public static ArticleResponseDto of(Long id, String title, String content, String name, LocalDateTime createdAt) {
        return new ArticleResponseDto(id, title, content, name, createdAt);
    }

    public static ArticleResponseDto from (ArticleDto dto){
        return new ArticleResponseDto(
            dto.id(),
            dto.title(),
            dto.content(),
            dto.member().name(),
            dto.createdAt()
        );
    }
}