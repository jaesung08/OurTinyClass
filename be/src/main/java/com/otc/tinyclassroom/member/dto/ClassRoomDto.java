package com.otc.tinyclassroom.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otc.tinyclassroom.member.entity.ClassRoom;

/**
 * 반 DTO.
 * DTO for  {@link com.otc.tinyclassroom.member.entity.ClassRoom}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ClassRoomDto(Long id, int year, int grade, int number) {

    public static ClassRoomDto of(Long id, int year, int grade, int number) {
        return new ClassRoomDto(id, year, grade, number);
    }

    /**
     * class room 엔티티로 부터 변환하는 메소드.
     */
    public static ClassRoomDto from(ClassRoom classRoom) {
        if (classRoom == null) {
            return null;
        }
        return new ClassRoomDto(
            classRoom.getId(),
            classRoom.getYear(),
            classRoom.getGrade(),
            classRoom.getNumber()
        );
    }
}