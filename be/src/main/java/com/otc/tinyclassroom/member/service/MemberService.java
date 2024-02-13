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
 * 회원 Service.
 */
@Service
@AllArgsConstructor
public class MemberService {

    // 회원가입 초기화 포인트
    static final int INITIAL_POINT = 0;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;


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
        memberRepository.findByMemberId(request.memberId()).ifPresent(
            member -> {
                throw new MemberException(MemberErrorCode.DUPLICATED_USER_NAME);
            }
        );
        // 비밀번호 형식 확인
        if (!isValidPassword(request.password())) {
            throw new MemberException(MemberErrorCode.PASSWORD_VALIDATION_FAILED);
        }
        // 이메일 확인
        if (!isValidEmail(request.email())) {
            throw new MemberException(MemberErrorCode.INVALID_EMAIL);
        }
        Member member = Member
            .of(request.memberId(), null, passwordEncoder.encode(request.password()), request.name(), request.email(), request.birthday(), INITIAL_POINT, null);

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
