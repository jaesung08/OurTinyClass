package com.otc.tinyclassroom.community.controller;

import com.otc.tinyclassroom.community.dto.request.ArticleCreateRequestDto;
import com.otc.tinyclassroom.community.dto.request.ArticleUpdateRequestDto;
import com.otc.tinyclassroom.community.dto.response.ArticleResponseDto;
import com.otc.tinyclassroom.community.dto.response.ArticleWithCommentsResponseDto;
import com.otc.tinyclassroom.community.entity.type.ArticleType;
import com.otc.tinyclassroom.community.entity.type.SearchType;
import com.otc.tinyclassroom.community.service.ArticleService;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.member.entity.ClassRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 게시글 controller dto.
 */
@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    /**
     * 전체 커뮤니티의 게시글 목록 조회.
     */
    @GetMapping("/articles")
    public BaseResponse<Page<ArticleResponseDto>> getArticleList(
        @RequestParam(required = true) String boardType,
        @RequestParam(required = false) String searchType,
        @RequestParam(required = false) String searchValue,
        @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        return BaseResponse.success(HttpStatus.OK.value(), "게시글 목록 반환 성공",
            articleService.searchArticles(null, ArticleType.fromString(boardType), SearchType.fromString(searchType), searchValue, pageable).map(ArticleResponseDto::from));
    }

    @PostMapping("/articles")
    public BaseResponse<Void> createArticle(@RequestBody ArticleCreateRequestDto articleCreateRequestDto) {
        // TODO: 토큰으로부터 멤버 아이디 (Long) 추출하기
        Long memberId = 1L;
        articleService.createArticle(memberId, articleCreateRequestDto);
        return BaseResponse.success(HttpStatus.CREATED.value(), "게시글 작성 완료", null);
    }

    @DeleteMapping("/articles/{articleId}")
    public BaseResponse<Void> removeArticle(@PathVariable("articleId") Long articleId) {
        //TODO: 토큰으로부터 같은 유저가 보낸 요청인지 확인하기
        articleService.removeArticle(articleId);
        return BaseResponse.success(HttpStatus.OK.value(), "게시글 삭제 성공", null);
    }

    @PatchMapping("/articles/{articleId}")
    public BaseResponse<Void> updateArticle(@PathVariable("articleId") Long articleId,
        @RequestBody ArticleUpdateRequestDto articleUpdateRequestDto) {
        //TODO: 토큰으로부터 같은 유저가 보낸 요청인지 확인하기
        articleService.updateArticle(articleId, articleUpdateRequestDto);
        return BaseResponse.success(HttpStatus.OK.value(), "게시글 삭제 성공", null);
    }

    @GetMapping("/articles/{articleId}")
    public BaseResponse<ArticleWithCommentsResponseDto> getArticleDetail(@PathVariable("articleId") Long articleId) {
        ArticleWithCommentsResponseDto article = ArticleWithCommentsResponseDto.from(articleService.getArticle(articleId));
        return BaseResponse.success(HttpStatus.OK.value(), "게시글 반환성공", article);
    }

    /**
     * 커뮤니티의 반별 게시판 조회
     */
    @GetMapping("/classRoom/{classRoomId}/articles")
    public BaseResponse<Page<ArticleResponseDto>> getArticleListForClassRoom(
        @PathVariable("classRoomId") Long classRoomId,
        @RequestParam(required = true) String boardType,
        @RequestParam(required = false) String searchType,
        @RequestParam(required = false) String searchValue,
        @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        return BaseResponse.success(HttpStatus.OK.value(), "게시글 목록 반환 성공",
            articleService.searchArticles(classRoomId, ArticleType.fromString(boardType), SearchType.fromString(searchType), searchValue, pageable).map(ArticleResponseDto::from));
    }

    @PostMapping("/classRoom/{classRoomId}/articles")
    public BaseResponse<Void> createArticleForClassRoom(@RequestBody ArticleCreateRequestDto articleCreateRequestDto) {
        // TODO: 토큰으로부터 멤버 아이디 (Long) 추출하기
        Long memberId = 1L;
        articleService.createArticle(memberId, articleCreateRequestDto);
        return BaseResponse.success(HttpStatus.CREATED.value(), "게시글 작성 완료", null);
    }

    @DeleteMapping("/classRoom/{classRoomId}/articles/{articleId}")
    public BaseResponse<Void> removeArticleForClassRoom(@PathVariable("articleId") Long articleId) {
        //TODO: 토큰으로부터 같은 유저가 보낸 요청인지 확인하기
        articleService.removeArticle(articleId);
        return BaseResponse.success(HttpStatus.OK.value(), "게시글 삭제 성공", null);
    }

    @PatchMapping("/classRoom/{classRoomId}/articles/{articleId}")
    public BaseResponse<Void> updateArticleForClassRoom(@PathVariable("articleId") Long articleId,
        @RequestBody ArticleUpdateRequestDto articleUpdateRequestDto) {
        //TODO: 토큰으로부터 같은 유저가 보낸 요청인지 확인하기
        articleService.updateArticle(articleId, articleUpdateRequestDto);
        return BaseResponse.success(HttpStatus.OK.value(), "게시글 삭제 성공", null);
    }

    @GetMapping("/classRoom/{classRoomId}/articles/{articleId}")
    public BaseResponse<ArticleWithCommentsResponseDto> getArticleDetailForClassRoom(@PathVariable("articleId") Long articleId) {
        ArticleWithCommentsResponseDto article = ArticleWithCommentsResponseDto.from(articleService.getArticle(articleId));
        return BaseResponse.success(HttpStatus.OK.value(), "게시글 반환성공", article);
    }

}
