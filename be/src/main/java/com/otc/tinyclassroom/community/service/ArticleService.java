package com.otc.tinyclassroom.community.service;

import com.otc.tinyclassroom.community.dto.ArticleDto;
import com.otc.tinyclassroom.community.dto.ArticleWithCommentDto;
import com.otc.tinyclassroom.community.dto.request.ArticleCreateRequestDto;
import com.otc.tinyclassroom.community.dto.request.ArticleUpdateRequestDto;
import com.otc.tinyclassroom.community.entity.Article;
import com.otc.tinyclassroom.community.entity.type.ArticleType;
import com.otc.tinyclassroom.community.entity.type.SearchType;
import com.otc.tinyclassroom.community.exception.CommunityErrorCode;
import com.otc.tinyclassroom.community.exception.CommunityException;
import com.otc.tinyclassroom.community.repository.ArticleRepository;
import com.otc.tinyclassroom.member.entity.ClassRoom;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.ClassRoomRepository;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시글 service.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ClassRoomRepository classRoomRepository;

    /**
     * 게시물 조회.
     */
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(Long classRoomId, ArticleType articleType, SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            if (classRoomId == null) {
                return articleRepository.findByClassRoomIsNullAndArticleType(articleType, pageable).map(ArticleDto::from);
            }
            return articleRepository.findByClassRoom_IdAndArticleType(classRoomId, articleType, pageable).map(ArticleDto::from);
        }
        if (classRoomId == null) {
            return switch (searchType) {
                case TITLE -> articleRepository.findByClassRoomIsNullAndArticleTypeAndTitleContaining(articleType, searchKeyword, pageable).map(ArticleDto::from);
                case CONTENT -> articleRepository.findByClassRoomIsNullAndArticleTypeAndContentContaining(articleType, searchKeyword, pageable).map(ArticleDto::from);
                case TITLE_CONTENT ->
                    articleRepository.findByClassRoomIsNullAndArticleTypeAndTitleContainingOrContentContaining(articleType, searchKeyword, searchKeyword, pageable).map(ArticleDto::from);
                case NAME -> articleRepository.findByClassRoomIsNullAndArticleTypeAndMember_NameContaining(articleType, searchKeyword, pageable).map(ArticleDto::from);
            };
        }
        return switch (searchType) {
            case TITLE -> articleRepository.findByClassRoom_IdAndArticleTypeAndTitleContaining(classRoomId, articleType, searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByClassRoom_IdAndArticleTypeAndContentContaining(classRoomId, articleType, searchKeyword, pageable).map(ArticleDto::from);
            case TITLE_CONTENT ->
                articleRepository.findByClassRoom_IdAndArticleTypeAndTitleContainingOrContentContaining(classRoomId, articleType, searchKeyword, searchKeyword, pageable).map(ArticleDto::from);
            case NAME -> articleRepository.findByClassRoom_IdAndArticleTypeAndMember_Name(classRoomId, articleType, searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    public ArticleWithCommentDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
            () -> new CommunityException(CommunityErrorCode.ARTICLE_NOT_FOUND)
        );
        article.increaseHit();
        return ArticleWithCommentDto.from(article);

    }

    public void createArticle(Long id, ArticleCreateRequestDto request) {
        // member id 찾기
        Member member = memberRepository.findById(id).orElseThrow(
            () -> new CommunityException(CommunityErrorCode.MEMBER_NOT_FOUND)
        );
        // classRoom id 찾기, 존재하지 않는 경우 null 처리
        ClassRoom classRoom = Optional.ofNullable(request.classRoomId())
            .flatMap(classRoomRepository::findById)
            .orElse(null);
        Article article = Article.of(member, classRoom, request.title(), request.content(), ArticleType.fromString(request.articleType()));
        articleRepository.save(article);
    }

    public void removeArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public void updateArticle(Long articleId, ArticleUpdateRequestDto request) {
        Article article = articleRepository.findById(articleId).orElseThrow(
            () -> new CommunityException(CommunityErrorCode.ARTICLE_NOT_FOUND)
        );
        if (request.title() != null && !(request.title().isBlank())) {
            article.setTitle(request.title());
        }
        if (request.content() != null && !(request.content().isBlank())) {
            article.setContent(request.content());
        }
    }
}
