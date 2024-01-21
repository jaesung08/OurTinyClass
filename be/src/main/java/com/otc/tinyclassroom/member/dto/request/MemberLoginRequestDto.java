package com.otc.tinyclassroom.member.dto.request;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String loginId;
    private String password;
}
