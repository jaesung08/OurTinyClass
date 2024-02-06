package com.otc.tinyclassroom.member.dto.response;

import com.otc.tinyclassroom.member.entity.Member;
import java.io.Serializable;

public record MemberArticleListResponseDto(String memberId, String name) implements Serializable {

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
