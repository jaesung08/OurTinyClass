package com.otc.tinyclassroom.member.dto.response;

import com.otc.tinyclassroom.member.entity.Member;

public record MemberArticleListResponseDto(String memberId, String name) {

    public static MemberArticleListResponseDto of(String memberId, String name) {
        return new MemberArticleListResponseDto(memberId, name);
    }

    public static MemberArticleListResponseDto from(Member entity) {
        return new MemberArticleListResponseDto(
            entity.getMemberId(),
            entity.getName()
        );
    }


}
