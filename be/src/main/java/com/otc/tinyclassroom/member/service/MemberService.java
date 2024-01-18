package com.otc.tinyclassroom.member.service;

import com.otc.tinyclassroom.member.dto.request.MemberJoinRequestDto;
import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.exception.MemberErrorCode;
import com.otc.tinyclassroom.member.exception.MemberException;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    /**
     * 회원 가입 메소드.
     */
    @Transactional
    public void join(MemberJoinRequestDto dto) {
        System.out.println(dto);
        // 빈 필드 확인
        if (dto.memberId() == null || dto.password() == null || dto.name() == null || dto.name().isBlank() || dto.email() == null || dto.birthday() == null) {
            throw new MemberException(MemberErrorCode.INVALID_FIELD_VALUE);
        }
        // 아이디 형식 확인
        if (!isValidMemberId(dto.memberId())) {
            throw new MemberException(MemberErrorCode.INVALID_MEMBER_ID);
        }
        // 아이디 중복 확인
        memberRepository.findByMemberId(dto.memberId()).ifPresent(
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
        Member member = Member
                .of(dto.memberId(), passwordEncoder.encode(dto.password()), dto.name(), dto.email(), dto.birthday(), INITIAL_POINT);
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
}
