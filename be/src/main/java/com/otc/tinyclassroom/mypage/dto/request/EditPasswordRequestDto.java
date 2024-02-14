package com.otc.tinyclassroom.mypage.dto.request;

/**
 * 나의 정보 수정 요청 Dto.
 */
public record EditPasswordRequestDto(String name, String password) {

    public static EditPasswordRequestDto of(String name, String password) {
        return new EditPasswordRequestDto(name, password);
    }
}
