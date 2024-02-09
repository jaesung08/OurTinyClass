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

    List<ScheduleListDto> findScheduleListByMemberId(String memberId, LocalDate start);

    long deleteScheduleById(Long id);

    Optional<ScheduleCheckDto> findScheduleById(Long id);

    Optional<ScheduleCheckDto> findScheduleByMemberIdAndScheduleDateAndTimeTable(Long memberId, LocalDate scheduleDate, Integer timeTalbe);
}
