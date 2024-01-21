package com.otc.tinyclassroom.board.repository;

import com.otc.tinyclassroom.board.entity.Article;
import com.otc.tinyclassroom.board.entity.QArticle;
import com.otc.tinyclassroom.board.entity.type.ArticleType;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

/**
 * 게시글 관련 Repository.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>,
    QuerydslPredicateExecutor<Article>,
    QuerydslBinderCustomizer<QArticle> {

    // 전체 커뮤니티
    Page<Article> findByClassRoomIsNullAndArticleTypeAndTitleContaining(ArticleType articleType, String title, Pageable pageable);

    Page<Article> findByClassRoomIsNullAndArticleTypeAndContentContaining(ArticleType articleType, String content, Pageable pageable);

    Page<Article> findByClassRoomIsNullAndArticleTypeAndTitleContainingOrContentContaining(ArticleType articleType, String title, String content, Pageable pageable);

    Page<Article> findByClassRoomIsNullAndArticleTypeAndMember_NameContaining(ArticleType articleType, String name, Pageable pageable);

    // 전체 게시판은 classRoom이 할당되지 않음
    Page<Article> findByClassRoomIsNullAndArticleType(ArticleType articleType, Pageable pageable);

    // 반 별 게시판
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        // 특정 property에 대한 검색 열기
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.member.name);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.member.name).first(StringExpression::containsIgnoreCase);
    }
}