package com.otc.tinyclassroom.board.dto.response;

import com.otc.tinyclassroom.board.dto.ArticleCommentDto;
import com.otc.tinyclassroom.board.dto.ArticleWithCommentDto;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.otc.tinyclassroom.board.dto.ArticleWithCommentDto}
 */
public record ArticleWithCommentsResponseDto(Long id, String title, String content, String name, Set<ArticleCommentDto> articleComments, LocalDateTime createdAt) implements Serializable {

    public static ArticleWithCommentsResponseDto of(Long id, String title, String content, String name, Set<ArticleCommentDto> articleComments, LocalDateTime createdAt) {
        return new ArticleWithCommentsResponseDto(id, title, content, name, articleComments, createdAt);
    }


//    public static ArticleWithCommentsResponseDto from(ArticleWithCommentDto dto) {
//        return new ArticleWithCommentsResponseDto(
//            dto.id(),
//            dto.title(),
//            dto.content(),
//            dto.member().name(),
//            dto.articleComments().stream().map()
//        );
//    }

}