package com.otc.tinyclassroom.lecture.controller;


import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.lecture.dto.request.RoomCreateRequestDto;
import com.otc.tinyclassroom.lecture.service.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 방 생성 요청 및 토큰 발급 Controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class WebClientController {

    private final WebClientService webClientService;

    /**
     * 방 생성 메서드.
     *
     * @param request 방 생성시 설정에 필요한 정보
     * @return 응답 성공여부와 url
     */
    @PostMapping("")
    public Mono<BaseResponse<String>> createRoom(@RequestBody RoomCreateRequestDto request) {
        Mono<String> url = webClientService.createClassRoomUrl(request);
        return url.flatMap(
            u -> Mono.just(BaseResponse.success(HttpStatus.OK.value(), "방 생성 성공", u))
        );
    }

    /**
     * 방 이름을 받아서 해당 방 URL 을 삭제하는 메소드.
     *
     * @param roomName 방 이름
     */
    @DeleteMapping("/{roomName}")
    public Mono<BaseResponse<Void>> removeRoom(@PathVariable String roomName) {
        Mono<Void> result = webClientService.deleteClassRoomUrl(roomName);
        return result.then(
            Mono.just(BaseResponse.success(HttpStatus.OK.value(), "방 삭제 성공", null))
        );
    }
}