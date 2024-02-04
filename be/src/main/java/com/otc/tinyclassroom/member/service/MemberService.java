package com.otc.tinyclassroom.member.service;

import com.otc.tinyclassroom.global.security.jwt.JwtProvider;
import com.otc.tinyclassroom.member.dto.MemberDto;
import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.dto.request.MemberUpdateRequestDto;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.entity.Role;
import com.otc.tinyclassroom.member.exception.MemberErrorCode;
import com.otc.tinyclassroom.member.exception.MemberException;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 서비스 로직 .
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
     * 회원 가입 메소드.
     */
    @Transactional
    public void join(MemberJoinRequestDto dto) {
        // 빈 필드 확인
        if (dto.memberId() == null || dto.password() == null || dto.name() == null || dto.name()
            .isBlank() || dto.email() == null || dto.birthday() == null) {
            throw new MemberException(MemberErrorCode.INVALID_FIELD_VALUE);
        }
        // 아이디 형식 확인
        if (!isValidMemberId(dto.memberId())) {
            throw new MemberException(MemberErrorCode.INVALID_MEMBER_ID);
        }
        // 아이디 중복 확인
        memberRepository.findByMemberIdAndDeletedAtIsNotNull(dto.memberId()).ifPresent(
            member -> {
                throw new MemberException(MemberErrorCode.DUPLICATED_USER_NAME);
            }
        );

        // 비밀번호 형식 확인
        if (!isValidPassword(dto.password())) {
            throw new MemberException(MemberErrorCode.PASSWORD_VALIDATION_FAILED);
        }
        // 이메일 확인
        if (!isValidEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.INVALID_EMAIL);
        }

        Member member = Member.of(dto.memberId(), null, passwordEncoder.encode(dto.password()),
            dto.name(), dto.email(), dto.birthday(), INITIAL_POINT);

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
    public List<MemberDto> getMemberList() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
            .map(MemberDto::from)
            .collect(Collectors.toList());
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
    public MemberDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));
        return MemberDto.from(member);
    }

    /**
     * 멤버 정보 수정 메서드(관리자).
     */
    @Transactional
    public MemberDto updateMember(Long memberId, MemberUpdateRequestDto updatedMemberDto) {
        checkAdmin();

        // 해당 memberId로 멤버 엔티티를 찾아옴
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        // 엔티티의 필드를 갱신
        member.updateMemberAdmin(updatedMemberDto.name(), updatedMemberDto.email(),
            updatedMemberDto.role());

        // 갱신된 엔티티를 저장
        memberRepository.save(member);

        return MemberDto.from(member);
    }


    /**
     * 요청한 유저의 권한이 있는지 확인(관리자).
     */
    public void checkAdmin() {
        Long currentMemberId = Long.valueOf(jwtProvider.getCurrentUserId());
        Member currentMember = memberRepository.findById(currentMemberId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NO_AUTHORITY));
        Role userRole = currentMember.getRole();
        if (userRole != Role.ROLE_ADMIN) {
            throw new MemberException(MemberErrorCode.NO_AUTHORITY);
        }
    }
}
