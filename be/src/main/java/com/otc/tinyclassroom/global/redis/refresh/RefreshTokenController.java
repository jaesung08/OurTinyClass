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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/redis")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/save")
    public ResponseEntity<String> refreshTokenRedis(String refreshToken, String memberId){
        refreshTokenService.save(refreshToken, memberId);
        return new ResponseEntity<String>(memberId, HttpStatus.OK);
    }
    @GetMapping("/refreshToken/{memberId}")
    public ResponseEntity<String> findByMemberId(@PathVariable("memberId") Long memberId) {
        Optional<String> refreshToken = refreshTokenService.findByMemberId(String.valueOf(memberId));

        if (refreshToken.isPresent()) {
            return new ResponseEntity<>(refreshToken.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ttl/{memberId}")
    public ResponseEntity<Long> findTTLByMemberId(@PathVariable("memberId") Long memberId) {
        Long restTTL =  refreshTokenService.getTTLByMemberId(String.valueOf(memberId));

        if (restTTL > 0) {
            return new ResponseEntity<>(restTTL, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @PostMapping("/refresh")
    public ResponseEntity<ReIssueResponseDto> refresh(String accessToken, @RequestBody String refreshToken) throws ClassNotFoundException {
        ReIssueResponseDto dto = refreshTokenService.reIssue(accessToken, refreshToken);
        System.out.println("1번. dto.accessToken() = " + dto.accessToken());
        if (dto != null) {
            // 새로 발급한 Access Token을 헤더에 넣어 클라이언트에게 전달
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + dto.accessToken());
            System.out.println("2번. dto.accessToken() = " + dto.accessToken());
            return new ResponseEntity<>(dto, headers, HttpStatus.OK);
        } else {
            // 새로 발급 실패 등의 상황에 대한 응답 처리
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
