package com.otc.tinyclassroom.board.repository;

import com.otc.tinyclassroom.board.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 게시글 관련 Repository.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}