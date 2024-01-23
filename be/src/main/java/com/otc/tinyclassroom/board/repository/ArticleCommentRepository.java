package com.otc.tinyclassroom.board.repository;

import com.otc.tinyclassroom.board.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 댓글 repository.
 */
@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

}