package com.otc.tinyclassroom.global.security.refresh;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RedisTemplate<String, String> redisTemplate;
    private final RefreshTokenRepository refreshTokenRepository;
    public void save(String memberId, String refreshToken_String) {
        RefreshToken refreshToken = new RefreshToken(memberId, refreshToken_String);
        refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByMemberId(String memberId){
        Optional<RefreshToken> refreshToken;
        try{
            refreshToken = refreshTokenRepository.findById(memberId);
        } catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
        return refreshToken;
    }

}
