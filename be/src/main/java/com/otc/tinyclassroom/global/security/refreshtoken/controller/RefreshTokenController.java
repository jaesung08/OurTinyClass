package com.otc.tinyclassroom.global.security.refreshtoken.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.refreshtoken.dto.request.RefreshRequestDto;
import com.otc.tinyclassroom.global.security.refreshtoken.dto.response.ReIssueResponseDto;
import com.otc.tinyclassroom.global.security.refreshtoken.dto.response.RefreshResponseDto;
import com.otc.tinyclassroom.global.security.refreshtoken.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Refresh Token 과 Redis 관련 로직을 처리하기 위한 컨트롤러.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/token")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    /**
     * memberId(Long)에 해당하는 Refresh Token 의 TTL(Time to Live)을 조회하는 엔드포인트.
     */
    @GetMapping("/ttl/{refreshToken}")
    public ResponseEntity<Long> findTtlByMemberId(@PathVariable("refreshToken") Long refreshToken) {
        Long restTtl =  refreshTokenService.getTtlByRefreshToken(String.valueOf(refreshToken));
        if (restTtl > 0) {
            return new ResponseEntity<>(restTtl, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Access Token 및 Refresh Token 을 사용하여 새로운 Access Token 을 발급하는 엔드포인트.
     */
    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<?>> refresh(@RequestBody RefreshRequestDto requestDto) {

        ReIssueResponseDto responseDto = refreshTokenService.reIssue(requestDto.refreshToken());
        // 새로 발급한 Access Token 을 헤더에 넣어 클라이언트에게 전달
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + responseDto.accessToken());
        RefreshResponseDto refreshResponseDto = new RefreshResponseDto(responseDto.refreshToken());
        return ResponseEntity.ok().headers(headers).body(BaseResponse.success(HttpStatus.OK.value(), "Access Token 이 재발급 되었습니다.", refreshResponseDto));
    }
}
