package com.otc.tinyclassroom.member.controller;

public record MemberClassRoomNumberResponseDto(Long classRoomId, int grade, int number, int year) {

    public static MemberClassRoomNumberResponseDto of(Long classRoomId, int grade, int number, int year) {
        return new MemberClassRoomNumberResponseDto(classRoomId, grade, number, year);
    }
}
