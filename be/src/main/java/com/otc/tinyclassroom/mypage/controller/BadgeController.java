package com.otc.tinyclassroom.mypage.controller;

import com.otc.tinyclassroom.mypage.dto.request.BadgeSaveRequestDto;
import com.otc.tinyclassroom.mypage.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 뱃지 Controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/badge")
public class BadgeController {

    private final BadgeService badgeService;

    @PostMapping("/make-badge")
    public void save(@RequestBody BadgeSaveRequestDto dto) {
        badgeService.save(dto);
    }
}
