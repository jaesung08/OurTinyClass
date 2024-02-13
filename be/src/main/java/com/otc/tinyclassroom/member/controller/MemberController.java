package com.otc.tinyclassroom.member.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.refreshtoken.service.RefreshTokenService;
import com.otc.tinyclassroom.media.service.MediaService;
import com.otc.tinyclassroom.member.dto.request.KakaoLoginRequestDto;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.dto.request.MentorRoleUpdateRequestDto;
import com.otc.tinyclassroom.member.dto.request.StudentRoleUpdateRequestDto;
import com.otc.tinyclassroom.member.dto.response.MemberLoginResponseDto;
import com.otc.tinyclassroom.member.service.CertificationService;
import com.otc.tinyclassroom.member.service.MemberService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 멤버 Controller.
 */
@RequestMapping("/api/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    private final MediaService mediaService;
    private final CertificationService certificationService;

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
     * 로그아웃 메서드. Refresh Token 을 무효화 함.
     */
    @PostMapping("/logout")
    public BaseResponse<Long> logoutMember() {
        Long memberId = refreshTokenService.getCurrentMemberId();
        String refreshToken = refreshTokenService.getRefreshTokenByUserId(memberId);
        refreshTokenService.deleteRefreshToken(refreshToken);

        return BaseResponse.success(HttpStatus.OK.value(), "로그아웃 성공!", memberId);
    }


    /**
     * 사용자 입장에서 학생 ROLE 변경 요청.
     */
    @PostMapping("/certification/student")
    public BaseResponse<List<String>> studentRoleUpdate(@RequestPart(name = "image") List<MultipartFile> files, @RequestPart(name = "request") StudentRoleUpdateRequestDto requestDto) {
        // TODO : ROLE_USER 일때만 해야하지 않을까?
        Map<String, List<String>> result = mediaService.storeImagesWithOriginalName(files);
        List<String> urlList = result.get("urlList");
        List<String> originalName = result.get("originalName");
        certificationService.saveStudent(originalName, urlList, requestDto);
        return BaseResponse.success(HttpStatus.OK.value(), "Student Role 업데이트 요청 성공!", urlList);
    }

    /**
     * 사용자 입장에서 Mentor ROLE 변경 요청.
     */
    @PostMapping("/certification/mentor")
    public BaseResponse<?> mentorRoleUpdate(@RequestParam("image") List<MultipartFile> files, @RequestPart(name = "request") MentorRoleUpdateRequestDto requestDto) {
        // TODO : ROLE_USER 일때만 해야하지 않을까?
        Map<String, List<String>> result = mediaService.storeImagesWithOriginalName(files);
        List<String> urlList = result.get("urlList");
        List<String> originalName = result.get("originalName");
        certificationService.saveMentor(originalName, urlList, requestDto);
        return BaseResponse.success(HttpStatus.OK.value(), "Mentor Role 업데이트 요청 성공!", urlList);
    }
}
