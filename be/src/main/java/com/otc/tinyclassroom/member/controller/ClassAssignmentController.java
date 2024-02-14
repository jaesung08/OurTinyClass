package com.otc.tinyclassroom.member.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.global.security.auth.PrincipalDetails;
import com.otc.tinyclassroom.member.dto.ClassRoomDto;
import com.otc.tinyclassroom.member.dto.request.AssignmentMembersRequestDto;
import com.otc.tinyclassroom.member.dto.request.RandomAssignmentMemberRequestDto;
import com.otc.tinyclassroom.member.dto.request.UpdateMemberClassRequestDto;
import com.otc.tinyclassroom.member.dto.response.MemberClassRoomResponseDto;
import com.otc.tinyclassroom.member.service.ClassAssignmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 반 편성 컨트롤러 정의. 반 개설, 반 배정, 반 수정, 반 조회
 */
@RequestMapping("/api/classrooms")
@RestController
@RequiredArgsConstructor
public class ClassAssignmentController {

    private final ClassAssignmentService classFormationService;

    /**
     * 반 생성.
     */
    @PostMapping("")
    public BaseResponse<ClassRoomDto> createClassRoom(@RequestBody ClassRoomDto classRoomDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        classFormationService.checkTeacherOrAdmin(principalDetails.getMember().getRole());
        ClassRoomDto createdClassRoom = classFormationService.createClassRoom(classRoomDto);
        return BaseResponse.success(HttpStatus.OK.value(), "반 개설에 성공하였습니다!", createdClassRoom);
    }

    /**
     * 학생들 반 편성.
     */
    @PatchMapping("/members/place")
    public BaseResponse<List<MemberClassRoomResponseDto>> placeMembers(@RequestBody AssignmentMembersRequestDto request, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        classFormationService.checkTeacherOrAdmin(principalDetails.getMember().getRole());
        List<MemberClassRoomResponseDto> placedMembers = classFormationService.placeMembers(request.memberIds(), request.classRoomId());
        return BaseResponse.success(HttpStatus.OK.value(), "멤버들을 배치하였습니다!", placedMembers);
    }

    /**
     * 학생(하나) 반 편성.
     */
    @PatchMapping("/member/updateClass")
    public BaseResponse<MemberClassRoomResponseDto> updateMemberClass(@RequestBody UpdateMemberClassRequestDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        classFormationService.checkTeacherOrAdmin(principalDetails.getMember().getRole());
        MemberClassRoomResponseDto updatedMember = classFormationService.updateMemberClass(dto.memberId(), dto.classRoomId());
        return BaseResponse.success(HttpStatus.OK.value(), "멤버 반 변경에 성공하였습니다!", updatedMember);
    }

    /**
     * 반 편성된 아이들 조회.
     */
    @GetMapping("/{classRoomId}/members")
    public BaseResponse<List<MemberClassRoomResponseDto>> getMembersByClassRoom(@PathVariable Long classRoomId) {
        List<MemberClassRoomResponseDto> members = classFormationService.getMembersByClassRoom(classRoomId);
        return BaseResponse.success(HttpStatus.OK.value(), "반의 멤버 조회에 성공하였습니다!", members);
    }

    /**
     * 랜덤으로 반 편성하기.
     */
    @PatchMapping("/randomAssignment")
    public BaseResponse<List<MemberClassRoomResponseDto>> randomAssignmentClassRooms(@RequestBody RandomAssignmentMemberRequestDto dto) {
        List<MemberClassRoomResponseDto> updatedMembers = classFormationService.randomAssignmentClassRooms(dto.classRoomId());
        return BaseResponse.success(HttpStatus.OK.value(), "랜덤으로 반을 부여하였습니다!", updatedMembers);
    }
}