package com.otc.tinyclassroom.member.service;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.global.security.refreshtoken.entity.RefreshToken;
import com.otc.tinyclassroom.global.security.refreshtoken.repository.RefreshTokenRepository;
import com.otc.tinyclassroom.member.client.KakaoClient;
import com.otc.tinyclassroom.member.dto.request.KakaoLoginRequestDto;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.dto.response.KakaoOAuthResponse;
import com.otc.tinyclassroom.member.dto.response.MemberLoginResponseDto;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.exception.MemberErrorCode;
import com.otc.tinyclassroom.member.exception.MemberException;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 Service.
 */
@Service
@AllArgsConstructor
@Transactional
public class MemberService {

    // 회원가입 초기화 포인트
    static final int INITIAL_POINT = 0;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private JwtProvider jwtProvider;

    /**
     * 회원가입을 수행한다.
     *
     * @param request 회원가입정보
     */
    public void join(MemberJoinRequestDto request) {
        // 빈 필드 확인
        if (request.memberId() == null || request.password() == null || request.name() == null || request.name().isBlank() || request.email() == null || request.birthday() == null) {
            throw new MemberException(MemberErrorCode.INVALID_FIELD_VALUE);
        }
        // 아이디 형식 확인
        if (!isValidMemberId(request.memberId())) {
            throw new MemberException(MemberErrorCode.INVALID_MEMBER_ID);
        }
        // 아이디 중복 확인
        memberRepository.findByMemberId(request.memberId()).ifPresent(
            member -> {
                throw new MemberException(MemberErrorCode.DUPLICATED_USER_NAME);
            }
        );
        // 비밀번호 형식 확인
        if (!isValidPassword(request.password())) {
            throw new MemberException(MemberErrorCode.PASSWORD_VALIDATION_FAILED);
        }
        // 이메일 확인
        if (!isValidEmail(request.email())) {
            throw new MemberException(MemberErrorCode.INVALID_EMAIL);
        }
        Member member = Member.of(request.memberId(), passwordEncoder.encode(request.password()), request.name(), request.email(), request.birthday(), INITIAL_POINT);

        memberRepository.save(member);
    }

    /**
     * 카카오 로그인.
     */
    public ResponseEntity<BaseResponse<MemberLoginResponseDto>> kakaoLogin(KakaoLoginRequestDto request) {
        KakaoClient kakaoClient = new KakaoClient();
        KakaoOAuthResponse response = kakaoClient.getKakaoProfile(request.accessToken());
        Member member = findOrCreateMember(response);

        String accessToken = jwtProvider.createAccessToken(member.getId(), member.getRole());
        System.out.println(accessToken);
        String refreshToken = UUID.randomUUID().toString();
        RefreshToken toRedis = new RefreshToken(refreshToken, member.getId().toString());

        refreshTokenRepository.save(toRedis);
        MemberLoginResponseDto responseDto = new MemberLoginResponseDto(
            member.getName(),
            member.getMemberId(),
            member.getRole().getValue(),
            member.getPoint(),
            refreshToken);
        return ResponseEntity.status(HttpStatus.OK)
            .header("Authorization", "Bearer " + accessToken)
            .body(
                BaseResponse.success(HttpStatus.OK.value(), "카카오 로그인 성공", responseDto)
            );
    }

    /**
     * 카카오 계정으로 로그인한 기록이 있으면 멤버를 가져오고 없는 경우 저장한다.
     */
    public Member findOrCreateMember(KakaoOAuthResponse response) {
        String memberId = "kakao-" + response.id();
        return memberRepository.findByMemberId(memberId)
            .orElseGet(
                () -> {
                    String dummyPassword = "dummy" + UUID.randomUUID();
                    Member newMember = Member.of(memberId, passwordEncoder.encode(dummyPassword), response.nickname(), null, null, INITIAL_POINT);
                    return memberRepository.save(newMember);
                }
            );
    }


    private boolean isValidMemberId(String memberId) {
        String memberRegex = "^[a-z0-9_-]{5,20}$";
        Pattern pattern = Pattern.compile(memberRegex);
        Matcher matcher = pattern.matcher(memberId);
        return matcher.matches();
    }

    protected boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    protected boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+{}:\"<>?\\[\\];',./`~])(?=\\S+$).{8,20}$";
        return password.matches(passwordRegex);
    }

}
