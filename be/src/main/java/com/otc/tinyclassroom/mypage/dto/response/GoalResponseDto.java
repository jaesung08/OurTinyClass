package com.otc.tinyclassroom.mypage.dto.response;

/**
 * 목표를 리턴하는 Dto.
 */
public record GoalResponseDto(String content, int achievement) {

    public static GoalResponseDto of(String content, int achievement) {
        return new GoalResponseDto(content, achievement);
    }
}
