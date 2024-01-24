package com.otc.tinyclassroom.media.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.otc.tinyclassroom.media.exception.MediaErrorCode;
import com.otc.tinyclassroom.media.exception.MediaException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 파일 확장자 검증 리스트
    private final List<String> fileValidateList = List
            .of(".jpg", ".jpeg", ".png", ".JPG", ".JPEG", ".PNG");

    public List<String> storeImages(List<MultipartFile> files) {

        List<String> urlList = new ArrayList<>();
        List<String> storeNames = new ArrayList<>();

        // 이미지명 검증 및 변환
        for (MultipartFile file : files) {
            storeNames.add(createUniqueFileName(file.getOriginalFilename()));
        }

        for (int i = 0; i < files.size(); i++) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(files.get(i).getContentType());
            metadata.setContentLength(files.get(i).getSize());

            try {
                // 아마존 s3에 파일 저장
                amazonS3Client.putObject(bucket, storeNames.get(i), files.get(i).getInputStream(), metadata);
                urlList.add(amazonS3Client.getResourceUrl(bucket, storeNames.get(i)));
            } catch (IOException e) {
                throw new MediaException(MediaErrorCode.INTERNAL_SERVER_ERROR);
            }
        }

        return urlList;
    }

    // 범용 고유 식별자를 이용해 파일명 중복 방지
    private String createUniqueFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileNameExtension(fileName));
    }

    // 파일 유효성 검사. 파일의 확장자 in (jpg, jpeg, png)
    private String getFileNameExtension(String fileName) {
        if (fileName.isEmpty()) {
            throw new MediaException(MediaErrorCode.NOT_EXIST_FILE_NAME);
        }

        String extension = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidateList.contains(extension)) {
            throw new MediaException(MediaErrorCode.WRONG_FILE_EXTENSION);
        }
        return extension;
    }

}
