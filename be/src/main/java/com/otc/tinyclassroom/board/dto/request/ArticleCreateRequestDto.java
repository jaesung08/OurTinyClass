package com.otc.tinyclassroom.board.dto.request;

import com.otc.tinyclassroom.board.entity.type.ArticleType;
import java.io.Serializable;
import java.util.Optional;

/**
 * DTO for {@link com.otc.tinyclassroom.board.entity.Article}
 */
public record ArticleCreateRequestDto(Long classRoomId, String title, String content, String articleType) implements Serializable {

    public static ArticleCreateRequestDto of(Long classRoomId, String title, String content, String articleType) {
        return new ArticleCreateRequestDto(classRoomId,title, content, articleType);
    }
}