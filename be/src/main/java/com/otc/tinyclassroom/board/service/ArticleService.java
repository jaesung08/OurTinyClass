package com.otc.tinyclassroom.board.service;

import com.otc.tinyclassroom.board.dto.ArticleDto;
import com.otc.tinyclassroom.board.entity.type.ArticleType;
import com.otc.tinyclassroom.board.entity.type.SearchType;
import com.otc.tinyclassroom.board.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(ArticleType articleType, SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findByClassRoomIsNullAndArticleType(articleType, pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByClassRoomIsNullAndArticleTypeAndTitleContaining(articleType, searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByClassRoomIsNullAndArticleTypeAndContentContaining(articleType, searchKeyword, pageable).map(ArticleDto::from);
            case TITLE_CONTENT -> articleRepository.findByClassRoomIsNullAndArticleTypeAndTitleContainingOrContentContaining(articleType, searchKeyword, searchKeyword, pageable).map(ArticleDto::from);
            case NAME -> articleRepository.findByClassRoomIsNullAndArticleTypeAndMember_NameContaining(articleType, searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    public ArticleDto getArticle(Long articleId) {
        return articleRepository.findById(articleId).map(ArticleDto::from)
            .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));
    }
}
