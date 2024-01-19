package com.otc.tinyclassroom.board.dto;

import com.otc.tinyclassroom.board.entity.Article;
import com.otc.tinyclassroom.board.entity.type.ArticleType;
import com.otc.tinyclassroom.member.dto.ClassRoomDto;
import com.otc.tinyclassroom.member.dto.MemberDto;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 게시글 DTO. DTO for {@link com.otc.tinyclassroom.board.entity.Article}
 */
public record ArticleDto(Long id, MemberDto member, ClassRoomDto classRoom, String title, String content, ArticleType articleType,
                         LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) implements Serializable {

    public static ArticleDto of(MemberDto member, ClassRoomDto classRoom, Long id, String title, String content, ArticleType articleType, LocalDateTime createdAt, String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {
        return new ArticleDto(id, member, classRoom, title, content, articleType, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    /**
     * article Entity로부터 ArticleDto로 변환하는 메소드.
     */
    public static ArticleDto from(Article entity) {
        return new ArticleDto(
            entity.getId(),
            MemberDto.from(entity.getMember()),
            ClassRoomDto.from(entity.getClassRoom()),
            entity.getTitle(),
            entity.getContent(),
            entity.getArticleType(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy()
        );
    }

    /**
     * ArticleDto를 Article로 변환해주는 메소드.
     */
    public static Article toEntity(ArticleDto dto) {
        return Article.of(
            MemberDto.toEntity(dto.member),
            ClassRoomDto.toEntity(dto.classRoom),
            dto.title,
            dto.content,
            dto.articleType
        );
    }
}
