package com.otc.tinyclassroom.mypage.service;

import com.otc.tinyclassroom.member.entity.Member;
import com.otc.tinyclassroom.member.exception.MemberErrorCode;
import com.otc.tinyclassroom.member.exception.MemberException;
import com.otc.tinyclassroom.member.repository.MemberRepository;
import com.otc.tinyclassroom.mypage.dto.request.BadgeSaveRequestDto;
import com.otc.tinyclassroom.mypage.dto.response.MemberBadgeResponseDto;
import com.otc.tinyclassroom.mypage.entity.Badge;
import com.otc.tinyclassroom.mypage.entity.BadgeEvent;
import com.otc.tinyclassroom.mypage.entity.MemberBadge;
import com.otc.tinyclassroom.mypage.exception.BadgeErrorCode;
import com.otc.tinyclassroom.mypage.exception.BadgeException;
import com.otc.tinyclassroom.mypage.repository.BadgeRepository;
import com.otc.tinyclassroom.mypage.repository.MemberBadgeRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 뱃지 관련 서비스.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BadgeService {

    private final MemberBadgeRepository memberBadgeRepository;
    private final MemberRepository memberRepository;
    private final BadgeRepository badgeRepository;
    private final ApplicationEventPublisher publisher;

    /**
     * 뱃지를 수여한다.
     */
    public void bestowBadge(Long memberId, Long badgeId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER)
        );
        Badge badge = badgeRepository.findById(badgeId).orElseThrow(
            () -> new BadgeException(BadgeErrorCode.NOT_FOUND_BADGE)
        );

        publisher.publishEvent(new BadgeEvent(badge, member));
    }

    /**
     * 가진 뱃지를 모두 가져온다.
     */
    public List<MemberBadgeResponseDto> getOnesBadge(Long memberId) {
        return memberBadgeRepository.findByMemberId(memberId).stream()
            .map(memberBadge -> MemberBadgeResponseDto.of(memberBadge.getBadge().getId(), memberBadge.getBadge().getImageUrl()))
            .collect(Collectors.toList());
    }

    /**
     * 각 조건에 변동이 있을 경우. Listener를 통해 뱃지 수여 조건을 확인하고 수여한다.
     */
    public void fulfillCondition(Long memberId, Long badgeId) {
        // 100번 접속함.
        // 1. Transaction에서 변경이 일어날 때 조건 확인 후 바로 업적 달성.
        // 2. Scheduler 처리할 때 조건 확인 후 업적 달성.
        Member member = memberRepository.findById(memberId).orElseThrow(
            () -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER)
        );
        Badge badge = badgeRepository.findById(badgeId).orElseThrow(
            () -> new BadgeException(BadgeErrorCode.NOT_FOUND_BADGE)
        );

        MemberBadge memberBadge = MemberBadge.of(badge, member);
        memberBadgeRepository.save(memberBadge);
    }

    public void save(BadgeSaveRequestDto dto) {
        Badge entity = new Badge(dto.image(), new HashSet<>());
        badgeRepository.save(entity);
    }
}
