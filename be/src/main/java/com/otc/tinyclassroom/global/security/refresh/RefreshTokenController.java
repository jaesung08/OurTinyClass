package com.otc.tinyclassroom.global.security.refresh;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/refreshToken")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/save")
    public ResponseEntity<String> refreshTokenRedis(String memberId, String refreshToken){
        refreshTokenService.save(memberId, refreshToken);
        return new ResponseEntity<String>(memberId, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<String> findByMemberId(@PathVariable String memberId){
        Optional<RefreshToken> refreshToken = refreshTokenService.findByMemberId(memberId);
        if (refreshToken.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(refreshToken.get().getRefreshToken(), HttpStatus.OK);
    }
}
