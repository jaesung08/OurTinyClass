package com.otc.tinyclassroom.community.repository;

import com.otc.tinyclassroom.community.entity.ArticleComment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 댓글 repository.
 */
@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    ArticleComment save(ArticleComment articleComment);
}