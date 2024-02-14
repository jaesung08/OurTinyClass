package com.otc.tinyclassroom.member.service;

import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.dto.request.MemberUpdateRequestDto;
import com.otc.tinyclassroom.member.dto.response.AdminMemberResponseDto;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.entity.Role;
import com.otc.tinyclassroom.member.exception.MemberErrorCode;
import com.otc.tinyclassroom.member.exception.MemberException;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 Service.
 */
@Service
@AllArgsConstructor
public class MemberService {

    // 회원가입 초기화 포인트
    static final int INITIAL_POINT = 0;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 회원가입을 수행한다.
     *
     * @param request 회원가입정보
     */
    @Transactional
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
        memberRepository.findByMemberIdAndDeletedAtIsNotNull(request.memberId()).ifPresent(member -> {
            throw new MemberException(MemberErrorCode.DUPLICATED_USER_NAME);
        });

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

    /**
     * 멤버 전체 목록 조회 메서드.
     */
    @Transactional
    public List<AdminMemberResponseDto> getMemberList() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(AdminMemberResponseDto::from).collect(Collectors.toList());
    }

    /**
     * 멤버 삭제 메서드.
     */
    @Transactional
    public void deleteMember(Long memberId) {
        checkAdmin();
        memberRepository.deleteById(memberId);
    }

    /**
     * 멤버 조회 메서드.
     */
    @Transactional
    public AdminMemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));
        return AdminMemberResponseDto.from(member);
    }

    /**
     * 멤버 정보 수정 메서드(관리자).
     */
    @Transactional
    public AdminMemberResponseDto updateMember(Long memberId, MemberUpdateRequestDto updatedMemberDto) {
        checkAdmin();

        // 해당 memberId로 멤버 엔티티를 찾아옴
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        // 업데이트할 필드가 null이 아닌 경우에만 Setter를 사용하여 엔티티의 필드를 갱신
        if (updatedMemberDto.name() != null) {
            member.setName(updatedMemberDto.name());
        }
        if (updatedMemberDto.email() != null) {
            member.setEmail(updatedMemberDto.email());
        }
        if (updatedMemberDto.role() != null) {
            member.setRole(updatedMemberDto.role());
        }
        return AdminMemberResponseDto.from(member);
    }


    /**
     * 요청한 유저의 권한이 있는지 확인(관리자).
     */
    public void checkAdmin() {
        Long currentMemberId = jwtProvider.getCurrentMemberId();
        Member currentMember = memberRepository.findById(currentMemberId).orElseThrow(() -> new MemberException(MemberErrorCode.NO_AUTHORITY));
        Role userRole = currentMember.getRole();
        if (userRole != Role.ROLE_ADMIN) {
            throw new MemberException(MemberErrorCode.NO_AUTHORITY);
        }
    }
}
