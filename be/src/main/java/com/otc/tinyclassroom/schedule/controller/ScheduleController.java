package com.otc.tinyclassroom.schedule.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.schedule.dto.request.ScheduleInsertRequestDto;
import com.otc.tinyclassroom.schedule.dto.response.ScheduleListResponseDto;
import com.otc.tinyclassroom.schedule.service.ScheduleService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 스케줄 컨트롤러.
 */
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 스케줄을 조회하여 해당 주의 스케줄 리스트 반환하는 메서드.
     */
    @GetMapping("/{memberId}")
    public BaseResponse<?> findScheduleList(@PathVariable("memberId") String memberId, @RequestParam("scheduleDate") LocalDate scheduleDate) {
        ScheduleListResponseDto scheduleList = scheduleService.getScheduleList(memberId, scheduleDate);

        return BaseResponse.success(HttpStatus.OK.value(), "스케줄 테이블이 반환되었습니다.", scheduleList);
    }

    /**
     * 스케줄 삽입 메서드.
     */
    @PostMapping("/insert")
    public BaseResponse<?> insertSchedule(@RequestBody ScheduleInsertRequestDto requestDto) {
        System.out.println("requestDto.lectureId() = " + requestDto.lectureId());

        scheduleService.insertSchedule(requestDto);
        return BaseResponse.success(HttpStatus.CREATED.value(), "스케줄이 추가되었습니다.", null);
    }

    /**
     * 스케줄 삭제 메서드.
     */
    @DeleteMapping("/delete/{id}")
    public BaseResponse<?> deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteSchedule(id);

        return BaseResponse.success(HttpStatus.OK.value(), "스케줄이 삭제되었습니다.", null);
    }
}
