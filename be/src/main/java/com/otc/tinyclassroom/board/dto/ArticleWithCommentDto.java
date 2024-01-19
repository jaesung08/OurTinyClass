package com.otc.tinyclassroom.board.dto;

import com.otc.tinyclassroom.board.entity.Article;
import com.otc.tinyclassroom.board.entity.type.ArticleType;
import com.otc.tinyclassroom.member.dto.ClassRoomDto;
import com.otc.tinyclassroom.member.dto.MemberDto;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.otc.tinyclassroom.board.entity.Article}
 */
public record ArticleWithCommentDto(Long id, MemberDto member, ClassRoomDto classRoom, String title, String content, ArticleType articleType, Set<ArticleCommentDto> articleComments,
                                    LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) implements Serializable {

    public static ArticleWithCommentDto of(Long id, MemberDto member, ClassRoomDto classRoom, String title, String content, ArticleType articleType, Set<ArticleCommentDto> articleComments,
        LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleWithCommentDto(id, member, classRoom, title, content, articleType, articleComments, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    /**
     * 게시글 entity로 부터 게시글.
     */
    public static ArticleWithCommentDto from(Article entity) {
        return ArticleWithCommentDto.of(
            entity.getId(),
            MemberDto.from(entity.getMember()),
            ClassRoomDto.from(entity.getClassRoom()),
            entity.getTitle(),
            entity.getContent(),
            entity.getArticleType(),
            entity.getArticleComments().stream()
                .map(ArticleCommentDto::from)
                .collect(Collectors.toCollection(LinkedHashSet::new)),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy()
        );
    }
}