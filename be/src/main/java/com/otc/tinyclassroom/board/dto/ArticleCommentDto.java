package com.otc.tinyclassroom.board.dto;

import com.otc.tinyclassroom.board.entity.Article;
import com.otc.tinyclassroom.board.entity.ArticleComment;
import com.otc.tinyclassroom.member.dto.MemberDto;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.otc.tinyclassroom.board.entity.ArticleComment}
 */
public record ArticleCommentDto(Long id, MemberDto member, ArticleDto article, String content,
                                LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) implements Serializable {

    public static ArticleCommentDto of(Long id, MemberDto member, ArticleDto article, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleCommentDto(id, member, article, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }


    /**
     * Article Comment Dto로 변환하는 메소드.
     */
    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
            entity.getId(),
            MemberDto.from(entity.getMember()),
            ArticleDto.from(entity.getArticle()),
            entity.getContent(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy()
        );
    }

    /**
     * 게시글 Dto를 게시글 Entity로 변환하는 메소드.
     */
    public static ArticleComment toEntity(ArticleCommentDto dto) {
        return ArticleComment.of(
            MemberDto.toEntity(dto.member()),
            ArticleDto.toEntity(dto.article()),
            dto.content()
        );
    }

}