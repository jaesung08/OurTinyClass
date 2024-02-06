package com.otc.tinyclassroom.community.dto.response;

import com.otc.tinyclassroom.community.dto.ArticleCommentDto;
import com.otc.tinyclassroom.community.entity.Article;
import com.otc.tinyclassroom.community.entity.type.ArticleType;
import com.otc.tinyclassroom.member.dto.ClassRoomDto;
import com.otc.tinyclassroom.member.dto.response.MemberArticleListResponseDto;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 게시물 전체 반환 리스트
 */
public record ArticleListResponseDto(Long id, MemberArticleListResponseDto member, ClassRoomDto classRoom, String title, String content, ArticleType articleType, int hit,
                                     Set<ArticleCommentDto> articleComments,
                                     LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) implements Serializable {

    public static ArticleListResponseDto of(Long id, MemberArticleListResponseDto member, ClassRoomDto classRoom, String title, String content, ArticleType articleType, int hit,
        Set<ArticleCommentDto> articleComments,
        LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleListResponseDto(id, member, classRoom, title, content, articleType, hit, articleComments, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    /**
     * 게시글 entity로 부터 게시글.
     */
    public static ArticleListResponseDto from(Article entity) {
        return ArticleListResponseDto.of(
            entity.getId(),
            MemberArticleListResponseDto.from(entity.getMember()),
            ClassRoomDto.from(entity.getClassRoom()),
            entity.getTitle(),
            entity.getContent(),
            entity.getArticleType(),
            entity.getHit(),
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