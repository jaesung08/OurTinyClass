package com.otc.tinyclassroom.schedule.repository;

import com.otc.tinyclassroom.schedule.dto.ScheduleListDto;
import com.otc.tinyclassroom.schedule.dto.response.ScheduleDetailResponseDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Querydsl을 사용하는 repository의 interface.
 */
public interface ScheduleRepositoryCustom {

    List<ScheduleListDto> findScheduleListByMemberId(Long memberId, LocalDate start);

    long insertSchedule();

    long deleteScheduleById(Long id);

    Optional<ScheduleDetailResponseDto> findScheduleById(Long id);

    Optional<ScheduleDetailResponseDto> findScheduleByMemberIdAndScheduleDateAndTimeTable(Long memberId, LocalDate scheduleDate, Integer timeTalbe);
}
