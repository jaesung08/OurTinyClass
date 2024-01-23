package com.otc.tinyclassroom.media.controller;

import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping("/images")
    public BaseResponse<?> uploadImages(@RequestParam("image") List<MultipartFile> multipartFiles) {
        List<String> imageList = mediaService.storeImages(multipartFiles);
        return BaseResponse.success(HttpStatus.CREATED.value(), "이미지 링크가 생성되었습니다.", imageList);
    }
}
