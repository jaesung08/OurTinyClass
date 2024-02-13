package com.otc.tinyclassroom.member.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.refreshtoken.service.RefreshTokenService;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 멤버 Controller.
 */
@RequestMapping("/api/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    /**
     * 회원가입 요청을 처리한다.
     *
     * @param request 회원가입 정보
     * @return 응답 코드, 메세지
     */
    @PostMapping("/join")
    public BaseResponse<Void> join(@RequestBody MemberJoinRequestDto request) {
        memberService.join(request);
        return BaseResponse.success(HttpStatus.OK.value(), "회원가입에 성공하였습니다!", null);
    }


    /**
     * 로그아웃 요청을 처리한다.
     *
     * @return 응답코드, 메세지
     */
    @PostMapping("/logout")
    public BaseResponse<Void> logoutMember() {
        Long currentUserId = refreshTokenService.getCurrentMemberId();
        refreshTokenService.deleteRefreshToken(currentUserId);
        return BaseResponse.success(HttpStatus.OK.value(), "로그아웃 성공!", null);
    }
}
