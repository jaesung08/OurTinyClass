package com.otc.tinyclassroom.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otc.tinyclassroom.member.entity.ClassRoom;
import com.otc.tinyclassroom.member.entity.Member;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 기본 멤버 DTO.
 * DTO for {@link com.otc.tinyclassroom.member.entity.Member}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MemberDto(String memberId, Set<ClassRoomDto> classRooms, String password, String name, String email, LocalDate birthday, int point)  {

    public static MemberDto of(String memberId, Set<ClassRoomDto> classRooms, String password, String name, String email, LocalDate birthday, int point) {
        return new MemberDto(memberId, classRooms, password, name, email, birthday, point);
    }

    /**
     * Member 엔티티를 MemberDto로 변환하는 메소드.
     */
    public static MemberDto from(Member entity) {
        Set<ClassRoomDto> classRoomDto = entity.getClassRooms().stream()
            .map(ClassRoomDto::from)
            .collect(Collectors.toSet());

        return new MemberDto(
            entity.getMemberId(),
            classRoomDto,
            entity.getPassword(),
            entity.getName(),
            entity.getEmail(),
            entity.getBirthday(),
            entity.getPoint()
        );
    }

    /**
     * MemberDto를 Member로 변환하는 메소드.
     */
    public static Member toEntity(MemberDto dto) {
        Set<ClassRoom> classRooms = dto.classRooms().stream()
            .map(ClassRoomDto::toEntity)
            .collect(Collectors.toSet());

        return Member.of(
            dto.memberId(),
            classRooms,
            dto.password(),
            dto.name(),
            dto.email(),
            dto.birthday(),
            dto.point()
        );
    }
}
