package com.otc.tinyclassroom.member.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otc.tinyclassroom.member.entity.Member;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * ClassRoom 에 소속된 member(학생)에 대한 정보 반환을 위한 Dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MemberClassRoomResponseDto(String memberId, String name, LocalDate birthday) implements Serializable {

    public static MemberClassRoomResponseDto of(String memberId, String name, LocalDate birthday) {
        return new MemberClassRoomResponseDto(memberId, name, birthday);
    }

    /**
     * 멤버 엔티티를 MemberLectureResponseDto로 변환하는 메소드.
     */
    public static MemberClassRoomResponseDto from(Member entity) {
        return new MemberClassRoomResponseDto(
            entity.getMemberId(),
            entity.getName(),
            entity.getBirthday()
        );
    }
}