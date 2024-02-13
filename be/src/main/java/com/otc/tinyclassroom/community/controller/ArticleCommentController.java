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
     * 게시글 작성요청을 처리한다.
     *
     * @param request 게시글 작성정보
     * @return 응답코드, 메세지, 댓글 아이디
     */
    @PostMapping()
    public BaseResponse<Long> createArticleComment(@RequestBody ArticleCommentRequestDto request) {
        Long articleCommentId = articleCommentService.createArticleComment(articleCommentService.getCurrentMemberId(), request);
        return BaseResponse.success(HttpStatus.CREATED.value(), "댓글 작성완료", articleCommentId);
    }

    /**
     * 댓글 수정 요청을 처리한다.
     *
     * @param articleCommentId 수정할 댓글 아이디
     * @param request          수정 정보
     * @return 응답코드, 메세지, 댓글 아이디
     */
    @PatchMapping("/{articleCommentId}")
    public BaseResponse<Void> updateArticleComment(@PathVariable("articleCommentId") Long articleCommentId,
        @RequestBody ArticleCommentUpdateRequestDto request) {
        articleCommentService.validateAuthority(articleCommentId);
        articleCommentService.updateArticleComment(articleCommentId, request);
        return BaseResponse.success(HttpStatus.OK.value(), "댓글 수정 성공", null);
    }

    /**
     * 댓글 삭제요청을 처리한다.
     *
     * @param articleCommentId 댓글 아이디
     * @return 응답코드, 메세지
     */
    @DeleteMapping("/{articleCommentId}")
    public BaseResponse<Void> deleteArticleComment(@PathVariable("articleCommentId") Long articleCommentId) {
        articleCommentService.validateAuthority(articleCommentId);
        articleCommentService.deleteArticleComment(articleCommentId);
        return BaseResponse.success(HttpStatus.NO_CONTENT.value(), "댓글 삭제 성공", null);
    }
}
