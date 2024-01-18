package com.otc.tinyclassroom.attendance.service;

import com.otc.tinyclassroom.attendance.dto.AttendanceDto;
import com.otc.tinyclassroom.attendance.entity.Attendance;
import com.otc.tinyclassroom.attendance.entity.AttendanceStatus;
import com.otc.tinyclassroom.attendance.repository.AttendanceRepository;
import com.otc.tinyclassroom.member.dto.MemberDto;
import com.otc.tinyclassroom.member.service.MemberService;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final MemberService memberService;

    public AttendanceService(AttendanceRepository attendanceRepository, MemberService memberService) {
        this.attendanceRepository = attendanceRepository;
        this.memberService = memberService;
    }

    public AttendanceDto checkIn(String memberId, LocalDateTime checkInTime) {
        // 이미 출석 기록이 있는지 확인
        if (attendanceRepository.existsByMember_MemberIdAndCheckOutIsNull(memberId)) {
            throw new RuntimeException("Already checked in.");
        }

        // MemberDto 가져오기
        MemberDto memberDto = memberService.getMemberById(memberId);

        // 출석 시간이 8:30 ~ 9:00 사이인지 확인
        AttendanceStatus status = getStatusForCheckIn(checkInTime);

        // 출석 기록 저장
        Attendance attendance = new Attendance(memberDto, checkInTime, null, status);
        attendanceRepository.save(attendance);

        return AttendanceDto.fromEntity(attendance);
    }

    public AttendanceDto checkOut(String memberId, LocalDateTime checkOutTime) {
        // 출석 기록이 없는 경우
        Attendance attendance = attendanceRepository.findByMember_MemberIdAndCheckOutIsNull(memberId)
            .orElseThrow(() -> new RuntimeException("No check-in record found."));

        // 퇴근 시간 설정
        attendance.setCheck_out(checkOutTime);

        // 퇴근 시간이 18:00 이전인 경우 출석 상태로 처리
        if (checkOutTime.toLocalTime().isBefore(LocalDateTime.of(1, 1, 1, 18, 0).toLocalTime())) {
            attendance.setStatus(AttendanceStatus.ATTENDANCE);
        }

        attendanceRepository.save(attendance);

        return AttendanceDto.fromEntity(attendance);
    }

    private AttendanceStatus getStatusForCheckIn(LocalDateTime checkInTime) {
        // 출석 시간이 8:30 ~ 9:00 사이인지 확인
        if (checkInTime.toLocalTime().isAfter(LocalDateTime.of(1, 1, 1, 8, 30).toLocalTime()) &&
            checkInTime.toLocalTime().isBefore(LocalDateTime.of(1, 1, 1, 9, 0).toLocalTime())) {
            return AttendanceStatus.ATTENDANCE;
        } else {
            return AttendanceStatus.LATE;
        }
    }
}
}
