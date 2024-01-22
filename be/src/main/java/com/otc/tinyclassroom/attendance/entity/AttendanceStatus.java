package com.otc.tinyclassroom.attendance.entity;

/**
 * 출석시 상태 지정
 * 정상 출석시 0
 * 지각시 1
 * 조퇴시 2
 * 지각이나 조퇴하였으나 시간이 부족할시 3
 * 지각이나 조퇴일때 소명했을 시 4
 */
public enum AttendanceStatus {
    ATTENDANCE, // 0
    LATE,  // 1
    LEAVE_EARLY, // 2
    ABSENCE, // 3
    LATE_WITH_EXCUSE  // 3
}