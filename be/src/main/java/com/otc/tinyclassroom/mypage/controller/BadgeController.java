package com.otc.tinyclassroom.mypage.controller;

import com.otc.tinyclassroom.mypage.dto.request.BadgeSaveRequestDto;
import com.otc.tinyclassroom.mypage.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class BadgeController {

    private final BadgeService badgeService;

    @PostMapping("/register/{memberId}")
    public void register(@PathVariable Long memberId, @RequestParam Long badgeId) {
        badgeService.bestowBadge(memberId, badgeId);

    }

    @PostMapping("/make-badge")
    public void save(@RequestBody BadgeSaveRequestDto dto) {
        badgeService.save(dto);
    }
}
