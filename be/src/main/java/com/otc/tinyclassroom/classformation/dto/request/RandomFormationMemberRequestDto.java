package com.otc.tinyclassroom.classformation.dto.request;

/**
 * 랜덤한 반 배정을 위한 request 를 위한 dto.
 */
public record RandomFormationMemberRequestDto(int targetGrade, int targetYear, int year1, int year2) {

}
