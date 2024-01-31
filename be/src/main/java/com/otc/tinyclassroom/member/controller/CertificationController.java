package com.otc.tinyclassroom.member.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.member.dto.response.RoleUpdateResponseDto;
import com.otc.tinyclassroom.member.entity.MentorRoleUpdateRequest;
import com.otc.tinyclassroom.member.entity.Role;
import com.otc.tinyclassroom.member.entity.StudentRoleUpdateRequest;
import com.otc.tinyclassroom.member.service.CertificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ADMIN 입장에서 등업 요청을 관리하기 위한 컨트롤러.
 */
@RestController
@RequestMapping("/api/admin/members/certification")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    /**
     * 학생 등업 리스트 조회.
     */
    @GetMapping("/student")
    public BaseResponse<List<StudentRoleUpdateRequest>> getStudentUpdateRequestList() {
        List<StudentRoleUpdateRequest> studentAll = certificationService.findStudentAll();
        return BaseResponse.success(HttpStatus.OK.value(), "학생 등업 리스트 조회성공", studentAll);
    }

    /**
     * 멘토 등업 리스트 조회.
     */
    @GetMapping("/mentor")
    public BaseResponse<List<MentorRoleUpdateRequest>> getMentorUpdateRequestList() {
        List<MentorRoleUpdateRequest> mentorAll = certificationService.findMentorAll();
        return BaseResponse.success(HttpStatus.OK.value(), "멘토 등업 리스트 조회성공", mentorAll);
    }

    /**
     * 학생 등업글 상세 조회.
     */
    @GetMapping("/student/{articleId}")
    public BaseResponse<StudentRoleUpdateRequest> getStudentUpdateRequestDetail(@PathVariable(name = "articleId") Long articleId) {
        StudentRoleUpdateRequest studentUpdateRequestById = certificationService.findStudentUpdateRequestById(articleId);
        return BaseResponse.success(HttpStatus.OK.value(), "학생 등업글 상세조회 성공", studentUpdateRequestById);
    }

    /**
     * 멘토 등업글 상세 조회.
     */
    @GetMapping("/mentor/{articleId}")
    public BaseResponse<MentorRoleUpdateRequest> getMentorUpdateRequestDetail(@PathVariable(name = "articleId") Long articleId) {
        MentorRoleUpdateRequest mentorUpdateRequestById = certificationService.findMentorUpdateRequestById(articleId);
        return BaseResponse.success(HttpStatus.OK.value(), "멘토 등업글 리스트 조회성공", mentorUpdateRequestById);
    }

    /**
     * 학생 등업 승인.
     */
    @PostMapping("/student/{articleId}/approve")
    public BaseResponse<RoleUpdateResponseDto> approveStudentRoleUpdate(@PathVariable(name = "articleId") Long articleId) {
        Long studentId = certificationService.findStudentIdByArticleId(articleId);
        RoleUpdateResponseDto result = certificationService.updateRole(studentId, Role.valueOf("ROLE_STUDENT"));
        certificationService.deleteStudentArticleByArticleId(articleId);
        return BaseResponse.success(HttpStatus.OK.value(), "학생 등업 성공", result);
    }

    /**
     * 멘토 등업 승인.
     */
    @PostMapping("/mentor/{articleId}/approve")
    public BaseResponse<RoleUpdateResponseDto> approveMentorRoleUpdate(@PathVariable(name = "articleId") Long articleId) {
        Long mentorId = certificationService.findMentorIdByArticleId(articleId);

        RoleUpdateResponseDto result = certificationService.updateRole(mentorId, Role.valueOf("ROLE_MENTOR"));
        certificationService.deleteMentorArticleByArticleId(articleId);
        return BaseResponse.success(HttpStatus.OK.value(), "멘토 등업 성공", result);
    }

    /**
     * 멘토 등업 거절.
     */
    @PostMapping("/mentor/{articleId}/deny")
    public BaseResponse<Long> denyMentorRoleUpdate(@PathVariable(name = "articleId") Long articleId) {
        certificationService.deleteMentorArticleByArticleId(articleId);
        return BaseResponse.success(HttpStatus.OK.value(), "멘토 등업 거절. 요청 삭제.", articleId);
    }

    /**
     * 학생 등업 거절.
     */
    @PostMapping("/student/{articleId}/deny")
    public BaseResponse<Long> denyStudentRoleUpdate(@PathVariable(name = "articleId") Long articleId) {
        certificationService.deleteStudentArticleByArticleId(articleId);
        return BaseResponse.success(HttpStatus.OK.value(), "학생 등업 거절. 요청 삭제.", articleId);
    }
}
