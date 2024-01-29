package com.otc.tinyclassroom.member.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.refreshtoken.service.RefreshTokenService;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 멤버 컨트롤러 정의. 로그인, 회원가입.
 */
@RequestMapping("/api/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    /**
     * 회원가입 메소드.
     */
    @PostMapping("/join")
    public BaseResponse<Void> join(@RequestBody MemberJoinRequestDto request) {
        memberService.join(request);
        return BaseResponse.success(HttpStatus.OK.value(), "회원가입에 성공하였습니다!", null);
    }

    /**
     * 현재 로그인한 사용자의 memberId 조회 메서드.
     */
    @GetMapping("/currentMember")
    public BaseResponse<String> currentMember() {
        String currentUserId = String.valueOf(refreshTokenService.getCurrentUserId());
        return BaseResponse.success(HttpStatus.OK.value(), "현재 로그인한 사용자의 `memberId`입니다.", currentUserId);
    }

    /**
     *  로그아웃 메서드.
     *  Refresh Token 을 무효화 함.
     */
    @PostMapping("/logout")
    public BaseResponse<String> logoutMember() {
        Long currentUserId = refreshTokenService.getCurrentUserId();
        refreshTokenService.deleteRefreshToken(currentUserId);

        return BaseResponse.success(HttpStatus.OK.value(), "로그아웃 성공!", String.valueOf(currentUserId));
    }
}
