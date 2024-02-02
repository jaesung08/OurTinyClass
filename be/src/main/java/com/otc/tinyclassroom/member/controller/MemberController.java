package com.otc.tinyclassroom.member.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.refreshtoken.service.RefreshTokenService;
import com.otc.tinyclassroom.media.service.MediaService;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.dto.request.MentorRoleUpdateRequestDto;
import com.otc.tinyclassroom.member.dto.request.StudentRoleUpdateRequestDto;
import com.otc.tinyclassroom.member.dto.response.RoleUpdateResponseDto;
import com.otc.tinyclassroom.member.service.CertificationService;
import com.otc.tinyclassroom.member.service.MemberService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 멤버 컨트롤러 정의. 로그인, 회원가입.
 */
@RequestMapping("/api/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MediaService mediaService;
    private final CertificationService certificationService;
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
        String refreshTokenByUserId = refreshTokenService.getRefreshTokenByUserId(refreshTokenService.getCurrentUserId());
        refreshTokenService.deleteRefreshToken(refreshTokenByUserId);

        return BaseResponse.success(HttpStatus.OK.value(), "로그아웃 성공!", String.valueOf(refreshTokenByUserId));
    }

    /**
     * 사용자 입장에서 학생 ROLE 변경 요청.
     */
    @PostMapping("/certification/student")
    public BaseResponse<RoleUpdateResponseDto> studentRoleUpdate(@RequestPart(name = "image") List<MultipartFile> files, @RequestPart(name = "userInfo") StudentRoleUpdateRequestDto requestDto) {
        // TODO : ROLE_USER 일때만 요청을 보낼 수 있도록 구현
        Map<String, List<String>> result = mediaService.storeImagesWithOriginalName(files);
        List<String> urlList = result.get("urlList");
        List<String> originalName = result.get("originalName");
        certificationService.saveStudent(originalName, urlList, requestDto);
        return BaseResponse.success(HttpStatus.OK.value(), "Student Role 업데이트 요청 성공!", null);
    }

    /**
     * 사용자 입장에서 Mentor ROLE 변경 요청.
     */
    @PostMapping("/certification/mentor")
    public BaseResponse<RoleUpdateResponseDto> mentorRoleUpdate(@RequestPart(name = "image") List<MultipartFile> files, @RequestPart(name = "userInfo") MentorRoleUpdateRequestDto requestDto) {
        // TODO : ROLE_USER 일때만 요청을 보낼 수 있도록 구현
        Map<String, List<String>> result = mediaService.storeImagesWithOriginalName(files);
        List<String> urlList = result.get("urlList");
        List<String> originalName = result.get("originalName");
        certificationService.saveMentor(originalName, urlList, requestDto);
        return BaseResponse.success(HttpStatus.OK.value(), "Mentor Role 업데이트 요청 성공!", null);
    }
}
