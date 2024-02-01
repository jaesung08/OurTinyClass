package com.otc.tinyclassroom.schedule.dto.response;

import com.otc.tinyclassroom.lecture.entity.type.LectureCategoryType;
import com.otc.tinyclassroom.lecture.entity.type.LectureType;
import java.time.LocalDate;

/**
 * 스케줄 디테일을 위한 Dto.
 *
 * @param id 스케줄 pk.
 * @param memberId 강의를 수강하고 있는 member pk.
 * @param title 강의 이름.
 * @param description 강의 설명.
 * @param lectureType 강의 타입.
 * @param lectureCategoryType 강의 분류.
 * @param scheduleDate 강의 날짜.
 * @param dayOfWeek 강의 요일.
 * @param timeTable 강의 교시.
 * @param deletable 삭제 가능 여부. 관리자가 해당 스케줄을 추가했으면 false.
 */
public record ScheduleDetailResponseDto(Long id, Long memberId, String title, String description, LectureType lectureType, LectureCategoryType lectureCategoryType, LocalDate scheduleDate,
                                        int dayOfWeek, int timeTable, Boolean deletable) {

    public static ScheduleDetailResponseDto of(Long id, Long memberId, String title, String description, LectureType lectureType, LectureCategoryType lectureCategoryType, LocalDate scheduleDate,
        int dayOfWeek, int timeTable,
        Boolean deletable) {
        return new ScheduleDetailResponseDto(id, memberId, title, description, lectureType, lectureCategoryType, scheduleDate, dayOfWeek, timeTable, deletable);
    }
}