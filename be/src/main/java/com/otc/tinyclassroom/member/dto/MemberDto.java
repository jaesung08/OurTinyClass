package com.otc.tinyclassroom.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otc.tinyclassroom.member.entity.Member;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 기본 멤버 DTO. DTO for {@link com.otc.tinyclassroom.member.entity.Member}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MemberDto(String memberId, String password, String name, String email, LocalDate birthday, int point) implements Serializable {

    public static MemberDto of(String memberId, String password, String name, String email, LocalDate birthday, int point) {
        return new MemberDto(memberId, password, name, email, birthday, point);
    }

    /**
     * Member 엔티티를 MemberDto로 변환하는 메소드.
     */
    public static MemberDto from(Member entity) {
        return new MemberDto(
            entity.getMemberId(),
            entity.getPassword(),
            entity.getName(),
            entity.getEmail(),
            entity.getBirthday(),
            entity.getPoint()
        );
    }

}