package com.otc.tinyclassroom.global.redis.refresh;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * memberId(Long)에 해당하는 Refresh Token 을 조회하는 엔드포인트.
     */
//    @GetMapping("/{memberId}")
//    public ResponseEntity<String> findByMemberId(@PathVariable("memberId") Long memberId) {
//        String refreshToken = refreshTokenService.findByMemberId(String.valueOf(memberId));
//
//        if (!refreshToken.isBlank()) {
//            return new ResponseEntity<>(refreshToken, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

    /**
     * memberId(Long)에 해당하는 Refresh Token 의 TTL(Time to Live)을 조회하는 엔드포인트.
     */
    @GetMapping("/ttl/{memberId}")
    public ResponseEntity<Long> findTtlByMemberId(@PathVariable("memberId") Long memberId) {
        Long restTtl =  refreshTokenService.getTtlByMemberId(String.valueOf(memberId));

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
    public BaseResponse<RefreshResponseDto> refresh(@RequestBody RefreshRequestDto requestDto) throws ClassNotFoundException {

        Optional<ReIssueResponseDto> dto = refreshTokenService.reIssue(requestDto.refreshToken());
        if (dto.isEmpty()) {
            throw new ClassNotFoundException();
        } else {
            ReIssueResponseDto responseDto = dto.orElse(null);
            if (dto.isPresent()) {
                // 새로 발급한 Access Token을 헤더에 넣어 클라이언트에게 전달
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Bearer " + responseDto.accessToken());
                RefreshResponseDto refreshResponseDto = new RefreshResponseDto(responseDto.refreshToken());
                return new BaseResponse<>(HttpStatus.OK.value(), "Access Token이 재발급 되었습니다.", refreshResponseDto);
            } else {
                // 새로 발급 실패 등의 상황에 대한 응답 처리
                return new BaseResponse<>(HttpStatus.UNAUTHORIZED.value(), "재발급에 실패하였습니다.", null);
            }

        }

    }
}
