package com.otc.tinyclassroom.board.repository;

import com.otc.tinyclassroom.board.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

}