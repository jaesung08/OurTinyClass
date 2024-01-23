package com.otc.tinyclassroom.board.controller;

import com.otc.tinyclassroom.board.dto.request.ArticleCommentRequestDto;
import com.otc.tinyclassroom.board.dto.request.ArticleCommentUpdateRequestDto;
import com.otc.tinyclassroom.board.service.ArticleCommentService;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping()
    public BaseResponse<Void> createArticleComment(@RequestBody ArticleCommentRequestDto request) {
        // TODO: 권한 체크
        Long memberId = 1L;
        articleCommentService.createArticleComment(memberId, request);
        return BaseResponse.success(HttpStatus.CREATED.value(), "댓글 작성완료", null);
    }

    @PatchMapping("/{articleCommentId}")
    public BaseResponse<Void> updateArticleComment(@PathVariable("articleCommentId") Long articleCommentId,
        @RequestBody ArticleCommentUpdateRequestDto request) {
        // TODO: 댓글 쓴 유저가 같은지 검증
        articleCommentService.updateArticleComment(articleCommentId, request);
        return BaseResponse.success(HttpStatus.OK.value(), "게시물 수정 성공", null);
    }

    @DeleteMapping("/{articleCommentId}")
    public BaseResponse<Void> removeArticleComment(@PathVariable("articleCommentId") Long articleCommentId) {
        articleCommentService.removeArticleComment(articleCommentId);
        return BaseResponse.success(HttpStatus.NO_CONTENT.value(), "댓글이 성공적으로 삭제되었습니다.", null);
    }
}
