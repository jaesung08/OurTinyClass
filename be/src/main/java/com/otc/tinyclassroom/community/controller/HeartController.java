package com.otc.tinyclassroom.community.controller;

import com.otc.tinyclassroom.community.service.HeartService;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 좋아요 컨트롤러.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/heart")
public class HeartController {

    private final HeartService heartService;

    /**
     * 좋아요 상태 업데이트.
     */
    @PostMapping("/{articleId}")
    public BaseResponse<String> update(@PathVariable("articleId") Long articleId) {
        boolean update = heartService.update(articleId);
        String result = update ? "추가" : "삭제";
        return BaseResponse.success(HttpStatus.OK.value(), "좋아요 상태를 변경했습니다.", result);
    }

}