package com.otc.tinyclassroom.board.service;

import com.otc.tinyclassroom.board.dto.ArticleDto;
import com.otc.tinyclassroom.board.dto.ArticleWithCommentDto;
import com.otc.tinyclassroom.board.dto.request.ArticleCreateRequestDto;
import com.otc.tinyclassroom.board.dto.request.ArticleUpdateRequestDto;
import com.otc.tinyclassroom.board.entity.Article;
import com.otc.tinyclassroom.board.entity.type.ArticleType;
import com.otc.tinyclassroom.board.entity.type.SearchType;
import com.otc.tinyclassroom.board.repository.ArticleRepository;
import com.otc.tinyclassroom.member.entity.ClassRoom;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.ClassRoomRepository;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  게시글 service.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ClassRoomRepository classRoomRepository;

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

    public ArticleWithCommentDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("게시글 없음 "));
        article.increaseHit();
        return ArticleWithCommentDto.from(article);

    }

    public void createArticle(Long id, ArticleCreateRequestDto request) {
        // member id 찾기
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));
        System.out.println(member);
        // classRoom id 찾기, 존재하지 않는 경우 null 처리
        ClassRoom classRoom = Optional.ofNullable(request.classRoomId())
            .flatMap(classRoomRepository::findById)
            .orElse(null);
        Article article = Article.of(member, classRoom, request.title(), request.content(), ArticleType.fromString(request.articleType()));
        System.out.println(article);
        articleRepository.save(article);
    }

    public void removeArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public void updateArticle(Long articleId, ArticleUpdateRequestDto request) {
        Article article = articleRepository.findById(articleId).orElseThrow(
            () -> new EntityNotFoundException("없는 게시글 입니다.")
        );
        if (request.title() != null && !(request.title().isBlank())) {
            article.setTitle(request.title());
        }
        if (request.content() != null && !(request.content().isBlank())) {
            article.setContent(request.content());
        }
    }
}
