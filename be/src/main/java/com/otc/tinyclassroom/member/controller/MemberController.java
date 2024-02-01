package com.otc.tinyclassroom.member.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.refreshtoken.service.RefreshTokenService;
import com.otc.tinyclassroom.member.dto.MemberDto;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.dto.request.MemberUpdateRequestDto;
import com.otc.tinyclassroom.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 로그아웃 메서드. Refresh Token 을 무효화 함.
     */
    @PostMapping("/logout")
    public BaseResponse<String> logoutMember() {
        Long currentUserId = refreshTokenService.getCurrentUserId();
        refreshTokenService.deleteRefreshToken(currentUserId);

        return BaseResponse.success(HttpStatus.OK.value(), "로그아웃 성공!", String.valueOf(currentUserId));
    }

    /**
     * 전체 멤버 목록을 반환하는 메서드.
     */
    @GetMapping("/getAllMembers")
    public BaseResponse<List<MemberDto>> getAllMemberList() {
        return BaseResponse.success(HttpStatus.OK.value(), "멤버 전체 목록을 불러왔습니다.", memberService.getAllMemberList());
    }

    /**
     * 제공받은 멤버 삭제하는 메서드.
     */
    @DeleteMapping("/delete-member/{memberId}")
    public BaseResponse<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return BaseResponse.success(HttpStatus.OK.value(), "멤버를 삭제하였습니다.", null);
    }

    /**
     * 멤버 정보 조회(불러오기).
     */
    @GetMapping("/{memberId}")
    public BaseResponse<MemberDto> getMember(@PathVariable Long memberId) {
        MemberDto member = memberService.getMember(memberId);
        return BaseResponse.success(HttpStatus.OK.value(), "멤버 조회에 성공하였습니다.", member);
    }

    /**
     * 멤버 정보 수정 메서드(관리자).
     */
    @PatchMapping("/update/{memberId}")
    public BaseResponse<MemberDto> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto updatedMemberDto) {
        MemberDto updatedMember = memberService.updateMember(memberId, updatedMemberDto);
        return BaseResponse.success(HttpStatus.OK.value(), "멤버 수정에 성공하였습니다.", updatedMember);
    }
}
