package com.otc.tinyclassroom.ocr.controller;

import com.google.api.Http;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.otc.tinyclassroom.global.common.model.response.BaseResponse;
import com.otc.tinyclassroom.ocr.dto.OCRRequestDto;
import com.otc.tinyclassroom.ocr.service.GoogleVisionOCRService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ocr")
public class OCRController {

    private final GoogleVisionOCRService ocrService;

    @PostMapping("")
    public BaseResponse<?> parseImageByGoogleVision(@RequestBody OCRRequestDto requestDto) throws IOException {
        String parsed = ocrService.detectText(requestDto.getImage());

        return new BaseResponse<String>(HttpStatus.OK.value(), "hlele", parsed);
    }
}
