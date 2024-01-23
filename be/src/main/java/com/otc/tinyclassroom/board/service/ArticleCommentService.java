package com.otc.tinyclassroom.board.service;

import com.otc.tinyclassroom.board.dto.request.ArticleCommentRequestDto;
import com.otc.tinyclassroom.board.dto.request.ArticleCommentUpdateRequestDto;
import com.otc.tinyclassroom.board.entity.Article;
import com.otc.tinyclassroom.board.entity.ArticleComment;
import com.otc.tinyclassroom.board.repository.ArticleCommentRepository;
import com.otc.tinyclassroom.board.repository.ArticleRepository;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 댓글 service.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final MemberRepository memberRepository;

    public void createArticleComment(Long memberId, ArticleCommentRequestDto request) {
        //멤버 찾기
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new EntityNotFoundException("Member not found")
        );
        // 게시글 찾기
        Article article = articleRepository.findById(request.articleId()).orElseThrow(
            () -> new EntityNotFoundException("Article not found")
        );
        ArticleComment articleComment = ArticleComment.of(member, article, request.content());
        articleCommentRepository.save(articleComment);
    }

    public void updateArticleComment(Long articleCommentId, ArticleCommentUpdateRequestDto request) {
        ArticleComment articleComment = articleCommentRepository.findById(articleCommentId).orElseThrow(
            () -> new EntityNotFoundException("없는 댓글입니다.")
        );
        if (request.content() != null && !(request.content().isBlank())) {
            articleComment.setContent(request.content());
        }
    }

    public void removeArticleComment(Long aritcleCommentId) {
        articleCommentRepository.deleteById(aritcleCommentId);
    }

}
