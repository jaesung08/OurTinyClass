package com.otc.tinyclassroom.community.controller;

import com.otc.tinyclassroom.community.dto.request.ArticleCommentRequestDto;
import com.otc.tinyclassroom.community.dto.request.ArticleCommentUpdateRequestDto;
import com.otc.tinyclassroom.community.service.ArticleCommentService;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 게시글 controller.
 */
@RequestMapping("/api/community/comments")
@RequiredArgsConstructor
@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    /**
     * 댓글 작성.
     */
    @PostMapping()
    public BaseResponse<Long> createArticleComment(@RequestBody ArticleCommentRequestDto request) {
        // TODO: 권한 체크
        Long memberId = 1L;
        Long articleCommentId = articleCommentService.createArticleComment(memberId, request);
        return BaseResponse.success(HttpStatus.CREATED.value(), "댓글 작성완료", articleCommentId);
    }

    /**
     * 댓글 수정.
     */
    @PatchMapping("/{articleCommentId}")
    public BaseResponse<Void> updateArticleComment(@PathVariable("articleCommentId") Long articleCommentId,
        @RequestBody ArticleCommentUpdateRequestDto request) {
        // TODO: 댓글 쓴 유저가 같은지 검증
        articleCommentService.updateArticleComment(articleCommentId, request);
        return BaseResponse.success(HttpStatus.OK.value(), "댓글 수정 성공", null);
    }

    @DeleteMapping("/{articleCommentId}")
    public BaseResponse<Void> removeArticleComment(@PathVariable("articleCommentId") Long articleCommentId) {
        articleCommentService.removeArticleComment(articleCommentId);
        return BaseResponse.success(HttpStatus.NO_CONTENT.value(), "댓글 삭제 성공", null);
    }
}
