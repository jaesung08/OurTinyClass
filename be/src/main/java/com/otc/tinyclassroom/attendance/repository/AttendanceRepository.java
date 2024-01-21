package com.otc.tinyclassroom.attendance.repository;

import com.otc.tinyclassroom.attendance.entity.Attendance;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // 해당 회원이 특정 시간 사이에 등교한 이력이 있는지 확인
    boolean existsByMemberIdAndCheckInBetween(Long memberId, Timestamp startTime, Timestamp endTime);

    // 해당 회원의 최근 등교 레코드를 가져오되, 아직 체크아웃하지 않은 경우
    Optional<Attendance> findTopByMemberIdAndCheckOutIsNullOrderByCheckInDesc(Long memberId);

    // 멤버 ID를 통해 조회하고 범위 내 시간 확인
    Optional<Attendance> findByMemberIdAndCheckInBetween(
        Long memberId,
        Timestamp startTime,
        Timestamp endTime
    );

    @Query("SELECT a FROM Attendance a WHERE a.member.id = :memberId AND a.checkIn BETWEEN :startOfMonth AND :endOfMonth")
    List<Attendance> findByMemberIdAndCheckInBetweenMonth(
        @Param("memberId") Long memberId,
        @Param("startOfMonth") Timestamp startOfMonth,
        @Param("endOfMonth") Timestamp endOfMonth
    );

    @Query("SELECT a FROM Attendance a WHERE a.member.id = :memberId AND a.checkIn BETWEEN :startOfDay AND :endOfDay")
    List<Attendance> findByMemberIdAndCheckInBetweenDay(
        @Param("memberId") Long memberId,
        @Param("startOfDay") Timestamp startOfDay,
        @Param("endOfDay") Timestamp endOfDay
    );

}
