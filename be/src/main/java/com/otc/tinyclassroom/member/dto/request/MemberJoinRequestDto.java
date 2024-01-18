package com.otc.tinyclassroom.member.dto.request;

import com.otc.tinyclassroom.member.entity.Member;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 회원 가입 요청을 위한 DTO. DTO for {@link com.otc.tinyclassroom.member.entity.Member}
 */
public record MemberJoinRequestDto(String memberId, String password, String name, String email, LocalDate birthday) implements Serializable {

    public static MemberJoinRequestDto of(String memberId, String password, String name, String email, LocalDate birthday) {
        return new MemberJoinRequestDto(memberId, password, name, email, birthday);
    }


    /**
     * MeemberDto 를 MemberJoinRequestDto로 변환하는 메소드.
     *
     * @return MemberJoinRequestDto
     */
    public static MemberJoinRequestDto from(Member entity) {
        return new MemberJoinRequestDto(
            entity.getMemberId(),
            entity.getPassword(),
            entity.getName(),
            entity.getEmail(),
            entity.getBirthday()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberJoinRequestDto that = (MemberJoinRequestDto) o;
        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}