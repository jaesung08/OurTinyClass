package com.otc.tinyclassroom.community.service;

import com.otc.tinyclassroom.community.dto.request.ArticleCommentRequestDto;
import com.otc.tinyclassroom.community.dto.request.ArticleCommentUpdateRequestDto;
import com.otc.tinyclassroom.community.entity.Article;
import com.otc.tinyclassroom.community.entity.ArticleComment;
import com.otc.tinyclassroom.community.exception.CommunityErrorCode;
import com.otc.tinyclassroom.community.exception.CommunityException;
import com.otc.tinyclassroom.community.repository.ArticleCommentRepository;
import com.otc.tinyclassroom.community.repository.ArticleRepository;
import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import java.util.Optional;
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
    private final JwtProvider jwtProvider;

    /**
     * 댓글 생성 메서드.
     */
    public Long createArticleComment(Long memberId, ArticleCommentRequestDto request) {
        //멤버 찾기
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new CommunityException(CommunityErrorCode.MEMBER_NOT_FOUND)
        );
        // 게시글 찾기
        Article article = articleRepository.findById(request.articleId()).orElseThrow(
            () -> new CommunityException(CommunityErrorCode.ARTICLE_NOT_FOUND)
        );
        ArticleComment articleComment = ArticleComment.of(member, article, request.content());
        return articleCommentRepository.save(articleComment).getId();
    }

    /**
     * 댓글 업데이트 메서드.
     */
    public void updateArticleComment(Long articleCommentId, ArticleCommentUpdateRequestDto request) {
        ArticleComment articleComment = articleCommentRepository.findById(articleCommentId).orElseThrow(
            () -> new CommunityException(CommunityErrorCode.ARTICLE_COMMENT_NOT_FOUND)
        );
        if (request.content() != null && !(request.content().isBlank())) {
            articleComment.setContent(request.content());
        }
    }

    /**
     * 댓글 삭제 메서드.
     */
    public void removeArticleComment(Long articleCommentId) {
        articleCommentRepository.deleteById(articleCommentId);
    }


    /**
     * 댓글의 작성자를 조회하는 메서드.
     */
    public Long getArticleCommentUserId(Long articleCommentId) {
        ArticleComment articleComment = articleCommentRepository.findById(articleCommentId)
            .orElseThrow(() -> new CommunityException(CommunityErrorCode.ARTICLE_NOT_FOUND));

        return Optional.ofNullable(articleComment.getMember())
            .map(Member::getId)
            .orElseThrow(() -> new CommunityException(CommunityErrorCode.MEMBER_NOT_FOUND));
    }

    /**
     * 댓글 권한이 있는지 확인한다.
     */
    public void validateAuthority(Long articleCommentId) {
        Long loginUserId = Long.valueOf(jwtProvider.getCurrentUserId());
        Long articleCommentUserId = this.getArticleCommentUserId(articleCommentId);
        if (!loginUserId.equals(articleCommentUserId)) {
            throw new CommunityException(CommunityErrorCode.HAVE_NO_AUTHORITY);
        }
    }

    /**
     * 현재 로그인 중인 사용자의 아이디를 리턴한다.
     */
    public Long getCurrentUserId() {
        return Long.valueOf(jwtProvider.getCurrentUserId());
    }

}
