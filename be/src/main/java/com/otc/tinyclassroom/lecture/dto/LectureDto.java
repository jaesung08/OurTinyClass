package com.otc.tinyclassroom.lecture.dto;

import com.otc.tinyclassroom.lecture.entity.Lecture;
import com.otc.tinyclassroom.lecture.entity.type.LectureCategoryType;
import com.otc.tinyclassroom.lecture.entity.type.LectureType;
import com.otc.tinyclassroom.member.dto.MemberDto;

/**
 * 강의 DTO.
 */
public record LectureDto(Long id, MemberDto memberDto, String title, String description, int dayOfWeek, int timeTable,
                         LectureType lectureType, LectureCategoryType lectureCategoryType, String lectureUrl) {

    public static LectureDto of(Long id, MemberDto memberDto, String title, String description, int dayOfWeek, int timeTable, LectureType lectureType, LectureCategoryType lectureCategoryType,
        String lectureUrl) {
        return new LectureDto(id, memberDto, title, description, dayOfWeek, timeTable, lectureType, lectureCategoryType, lectureUrl);
    }

    /**
     * Lecture entity -> lecture Dto.
     */
    public static LectureDto from(Lecture entity) {
        return new LectureDto(
            entity.getId(),
            MemberDto.from(entity.getMember()),
            entity.getTitle(),
            entity.getDescription(),
            entity.getDayOfWeek(),
            entity.getTimeTable(),
            entity.getLectureType(),
            entity.getLectureCategoryType(),
            entity.getLectureUrl()
        );
    }

    /**
     * Lecture Dto -> Lecture Entity.
     */
    public static Lecture toEntity(LectureDto lectureDto) {
        return Lecture.of(
            MemberDto.toEntity(lectureDto.memberDto),
            lectureDto.title,
            lectureDto.description,
            lectureDto.dayOfWeek,
            lectureDto.timeTable(),
            lectureDto.lectureType(),
            lectureDto.lectureCategoryType,
            lectureDto.lectureUrl
        );
    }
}
