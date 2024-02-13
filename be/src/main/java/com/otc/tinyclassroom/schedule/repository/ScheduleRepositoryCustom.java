package com.otc.tinyclassroom.schedule.repository;

import com.otc.tinyclassroom.schedule.dto.ScheduleCheckDto;
import com.otc.tinyclassroom.schedule.dto.ScheduleListDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Querydsl을 사용하는 repository의 interface.
 */
public interface ScheduleRepositoryCustom {

    List<ScheduleListDto> findScheduleListById(Long memberId, String teacherMemberId, LocalDate start);

    long deleteScheduleById(Long scheduleId);

    Optional<ScheduleCheckDto> findScheduleById(Long scheduleId);

    Optional<ScheduleCheckDto> findScheduleByMemberIdAndScheduleDateAndTimeTable(Long memberId, String teacherMemberId, LocalDate scheduleDate, Integer timeTable);
}
