package com.otc.tinyclassroom.community.dto.response;

import com.otc.tinyclassroom.community.dto.ArticleWithCommentDto;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.otc.tinyclassroom.community.dto.ArticleWithCommentDto}.
 */
public record ArticleWithCommentsResponseDto(Long id, String title, String content, String name, int hit, Set<ArticleCommentResponseDto> articleComments, LocalDateTime createdAt,
                                             LocalDateTime modifiedAt) implements Serializable {

    public static ArticleWithCommentsResponseDto of(Long id, String title, String content, String name, int hit, Set<ArticleCommentResponseDto> articleComments, LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
        return new ArticleWithCommentsResponseDto(id, title, content, name, hit, articleComments, createdAt, modifiedAt);
    }

    public static ArticleWithCommentsResponseDto from(ArticleWithCommentDto dto) {
        return new ArticleWithCommentsResponseDto(
            dto.id(),
            dto.title(),
            dto.content(),
            dto.member().name(),
            dto.hit(),
            dto.articleComments().stream()
                .map(ArticleCommentResponseDto::from)
                .collect(Collectors.toCollection(LinkedHashSet::new)),
            dto.createdAt(),
            dto.modifiedAt()
        );
    }

}