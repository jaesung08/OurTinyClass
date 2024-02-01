package com.otc.tinyclassroom.schedule.dto;

import com.otc.tinyclassroom.lecture.dto.LectureDto;
import com.otc.tinyclassroom.schedule.entity.Schedule;
import java.time.LocalDate;

/**
 * 스케줄 DTO.
 */
public record ScheduleDto(Long id, Long memberId, LectureDto lectureDto, LocalDate scheduleDate, Boolean deletable) {

    public static ScheduleDto of(Long id, Long memberId, LectureDto lectureDto, LocalDate scheduleDate, Boolean deletable) {
        return new ScheduleDto(id, memberId, lectureDto, scheduleDate, deletable);
    }

    /**
     * Schedule entity -> Schedule Dto.
     */
    public static ScheduleDto from(Schedule entity) {
        return ScheduleDto.of(
                entity.getId(),
                entity.getMemberId(),
                LectureDto.from(entity.getLecture()),
                entity.getScheduleDate(),
                entity.getDeletable()
        );
    }

    /**
     * Schedule Dto -> Schedule entity.
     */
    public static Schedule toEntity(ScheduleDto dto) {
        return Schedule.of(
                dto.memberId,
                LectureDto.toEntity(dto.lectureDto),
                dto.scheduleDate(),
                dto.deletable);
    }
}
