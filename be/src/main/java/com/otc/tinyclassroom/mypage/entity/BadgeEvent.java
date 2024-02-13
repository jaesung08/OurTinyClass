package com.otc.tinyclassroom.mypage.entity;

import com.otc.tinyclassroom.member.entity.Member;

public record BadgeEvent(Badge badge, Member member) {

}
