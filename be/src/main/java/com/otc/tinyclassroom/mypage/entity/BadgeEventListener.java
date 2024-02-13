package com.otc.tinyclassroom.mypage.entity;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.mypage.repository.MemberBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BadgeEventListener {

    private final MemberBadgeRepository memberBadgeRepository;

    @EventListener
    public BaseResponse<?> bestowBadge(BadgeEvent badgeEvent) {

        MemberBadge memberBadge = MemberBadge.of(badgeEvent.badge(), badgeEvent.member());
        if (memberBadgeRepository.findByBadgeIdAndMemberId(badgeEvent.badge().getId(), badgeEvent.member().getId()).isEmpty()) {
            memberBadgeRepository.save(memberBadge);
            Long badgeId = badgeEvent.badge().getId();
            return BaseResponse.success(HttpStatus.OK.value(), "뱃지 지급에 성공하였습니다.", badgeId);
        } else {
            return BaseResponse.error(HttpStatus.BAD_REQUEST.value(), "이미 갖고 있는 뱃지입니다.");
        }
    }

    @EventListener
    public void makeCoupon(BadgeEvent event) {
        System.out.println(event.badge().getId() + "님에게 쿠폰을 전송했습니다.");
    }
}
