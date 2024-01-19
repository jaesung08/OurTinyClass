package com.otc.tinyclassroom.attendance.repository;

import com.otc.tinyclassroom.attendance.entity.Attendance;
import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // 해당 회원이 특정 시간 사이에 등교한 이력이 있는지 확인
    boolean existsByMemberIdAndCheckInBetween(Long memberId, Timestamp startTime, Timestamp endTime);

    // 해당 회원의 최근 등교 레코드를 가져오되, 아직 체크아웃하지 않은 경우
    Optional<Attendance> findTopByMemberIdAndCheckOutIsNullOrderByCheckInDesc(Long memberId);

    Optional<Attendance> findByMemberIdAndCheckInBetween(
        Long memberId,
        Timestamp startTime,
        Timestamp endTime
    );

}
