package com.otc.tinyclassroom.member.service;

import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.member.dto.request.MentorRoleUpdateRequestDto;
import com.otc.tinyclassroom.member.dto.request.StudentRoleUpdateRequestDto;
import com.otc.tinyclassroom.member.dto.response.RoleUpdateResponseDto;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.entity.MentorRoleUpdateRequest;
import com.otc.tinyclassroom.member.entity.Role;
import com.otc.tinyclassroom.member.entity.StudentRoleUpdateRequest;
import com.otc.tinyclassroom.member.exception.MemberErrorCode;
import com.otc.tinyclassroom.member.exception.MemberException;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import com.otc.tinyclassroom.member.repository.MentorRoleUpdateRepository;
import com.otc.tinyclassroom.member.repository.StudentRoleUpdateRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 등업 요청을 관리하기 위한 서비스.
 */
@AllArgsConstructor
@Service
public class CertificationService {

    private final MentorRoleUpdateRepository mentorRoleUpdateRepository;
    private final StudentRoleUpdateRepository studentRoleUpdateRepository;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    /**
     * 유효성 검사 후 MentorRoleUpdateRequest 저장.
     */
    @Transactional
    public void saveMentor(List<String> originalFiles, List<String> savedFiles, MentorRoleUpdateRequestDto dto) {
        // content 가 없다면 exception 터뜨리기
        if (Objects.requireNonNullElse(dto.content(), "").isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해야 합니다.");
        }

        // belong 이 없다면 exception 터뜨리기
        if (Objects.requireNonNullElse(dto.belong(), "").isEmpty()) {
            throw new IllegalArgumentException("소속을 입력하여야 합니다.");
        }
        Member member = memberRepository.findById(Long.valueOf(jwtProvider.getCurrentUserId())).orElseThrow(
            () -> new MemberException(MemberErrorCode.INVALID_MEMBER_ID, "유저 아이디가 존재하지 않습니다.")
        );

        MentorRoleUpdateRequest mentorRoleUpdateRequest
            = MentorRoleUpdateRequest.of(member, dto.content(), dto.belong(), originalFiles, savedFiles);
        mentorRoleUpdateRepository.save(mentorRoleUpdateRequest);
    }

    /**
     * 유효성 검사 후 StudentRoleUpdateRequest 저장.
     */
    @Transactional
    public void saveStudent(List<String> originalFiles, List<String> savedFiles, StudentRoleUpdateRequestDto dto) {
        if (dto.beforeSchoolType() < 0 || dto.beforeSchoolType() > 3) {
            throw new IllegalArgumentException("이전 학교 Type이 올바르지 않습니다.");
        }

        if (dto.quitReason() < 0 || dto.quitReason() > 7) {
            throw new IllegalArgumentException("중퇴 사유가 올바르지 않습니다.");
        }
        Member member = memberRepository.findById(Long.valueOf(jwtProvider.getCurrentUserId())).orElseThrow(
            () -> new MemberException(MemberErrorCode.INVALID_MEMBER_ID, "유저 아이디가 존재하지 않습니다.")
        );
        StudentRoleUpdateRequest studentRoleUpdateRequest
            = StudentRoleUpdateRequest.of(member, dto.content(), dto.beforeSchoolType(), dto.quitReason(), originalFiles, savedFiles);
        studentRoleUpdateRepository.save(studentRoleUpdateRequest);

    }

    /**
     * ROLE_ADMIN 을 가진 관리자 계정만 사용가능 memberId로 가져온 뒤 Role 을 update 함.
     */
    @Transactional
    public RoleUpdateResponseDto updateRole(Long memberId, Role role) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new MemberException(MemberErrorCode.INVALID_MEMBER_ID, "ROLE 업데이트 유저를 찾을 수 없습니다.")
        );
        if (isAuthorizedForRoleChange()) {
            member.setRole(role);
            memberRepository.save(member);
        }
        return RoleUpdateResponseDto.of(memberId, role);
    }

    /**
     * 학생 등업 리스트 전체 조회.
     */
    public List<StudentRoleUpdateRequest> findStudentAll() {
        return studentRoleUpdateRepository.findAll();
    }

    /**
     * 멘토 등업 리스트 전체 조회.
     */
    public List<MentorRoleUpdateRequest> findMentorAll() {
        return mentorRoleUpdateRepository.findAll();
    }

    /**
     * 학생 등업 요청 상세 조회.
     */
    public StudentRoleUpdateRequest findStudentUpdateRequestById(Long articleId) {
        return studentRoleUpdateRepository.findById(articleId).orElseThrow(
            EntityNotFoundException::new
        );
    }

    /**
     * 멘토 등업 요청 상세 조회.
     */
    public MentorRoleUpdateRequest findMentorUpdateRequestById(Long articleId) {
        return mentorRoleUpdateRepository.findById(articleId).orElseThrow(
            EntityNotFoundException::new
        );
    }

    /**
     * 학생 등업 글로부터 유저 아이디를 가져옴.
     */
    public Long findStudentIdByArticleId(Long articleId) {
        StudentRoleUpdateRequest studentRoleUpdateRequest = studentRoleUpdateRepository.findById(articleId).orElseThrow(
            EntityNotFoundException::new
        );
        return studentRoleUpdateRequest.getMember().getId();
    }

    /**
     * 멘토 등업 글로부터 유저 아이디를 가져옴.
     */
    public Long findMentorIdByArticleId(Long articleId) {
        MentorRoleUpdateRequest mentorRoleUpdateRequest = mentorRoleUpdateRepository.findById(articleId).orElseThrow(
            EntityNotFoundException::new
        );
        return mentorRoleUpdateRequest.getMember().getId();
    }

    /**
     * 학생 요청이 완료되거나 거절시 삭제.
     */
    public void deleteStudentArticleByArticleId(Long articleId) {
        studentRoleUpdateRepository.deleteById(articleId);
    }

    /**
     * 멘토 요청이 완료되거나 거절시 삭제.
     */
    public void deleteMentorArticleByArticleId(Long articleId) {
        mentorRoleUpdateRepository.deleteById(articleId);
    }

    /**
     * ADMIN 유저만 role 을 변경할 수 있음.
     */
    private boolean isAuthorizedForRoleChange() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication != null && authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }


}
