package com.otc.tinyclassroom.media.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.media.service.MediaService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 미디어 업로드를 컨트롤러.
 */
@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    /**
     * 다중 파일 업로드.
     * 업로드 시 UUID로 파일명 변경.
     * 본래 파일명 또한 리턴해줌.
     */
    @PostMapping("/images")
    public BaseResponse<?> uploadImages(@RequestPart(name = "image") List<MultipartFile> multipartFiles) {
        Map<String, List<String>> result = mediaService.storeImages(multipartFiles);
        List<String> originalName = result.get("originalName");
        return BaseResponse.success(HttpStatus.CREATED.value(), "이미지 링크가 생성되었습니다.", originalName);
    }
}
