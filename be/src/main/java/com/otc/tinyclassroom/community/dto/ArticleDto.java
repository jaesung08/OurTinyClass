package com.otc.tinyclassroom.community.dto;

import com.otc.tinyclassroom.community.entity.Article;
import com.otc.tinyclassroom.community.entity.type.ArticleType;
import com.otc.tinyclassroom.member.dto.ClassRoomDto;
import com.otc.tinyclassroom.member.dto.MemberDto;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 게시글 DTO. DTO for {@link com.otc.tinyclassroom.community.entity.Article}
 */
public record ArticleDto(Long id, MemberDto member, ClassRoomDto classRoom, String title, String content, ArticleType articleType,
                         LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, int hit) implements Serializable {

    public static ArticleDto of(MemberDto member, ClassRoomDto classRoom, Long id, String title, String content, ArticleType articleType, LocalDateTime createdAt, String createdBy,
        LocalDateTime modifiedAt, String modifiedBy, int hit) {
        return new ArticleDto(id, member, classRoom, title, content, articleType, createdAt, createdBy, modifiedAt, modifiedBy, hit);
    }

    /**
     * article Entity로부터 ArticleDto로 변환하는 메소드.
     */
    public static ArticleDto from(Article entity) {
        ClassRoomDto classRoom = null;
        if (entity.getClassRoom() != null) {
            classRoom = ClassRoomDto.from(entity.getClassRoom());
        }
        return new ArticleDto(
            entity.getId(),
            MemberDto.from(entity.getMember()),
            classRoom,
            entity.getTitle(),
            entity.getContent(),
            entity.getArticleType(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy(),
            entity.getHit()
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
