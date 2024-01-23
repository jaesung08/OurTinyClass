package com.otc.tinyclassroom.ocr.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoogleVisionOCRService {

    public static String detectText(String url) throws IOException {

        long startTime = System.currentTimeMillis();

        /*
        * docs : https://cloud.google.com/vision/docs/ocr?hl=ko
        * */

        // 서버에 분석 요청을 보내기 위한 이미지의 리스트
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ImageSource imgSource = ImageSource.newBuilder().setImageUri(url).build();
        Image img = Image.newBuilder().setSource(imgSource).build();

        // DOCUMENT_TEXT_DETECTION : 책처럼 밀집된 문서에서 유리
        Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();

        // 서버로 분석 요청을 보내기 위해 AnnotateImageRequest 객체 생성.
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);


        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            // AnnotateImageRequest List를 클라이언트 요청에 담아 보낸뒤 response 객체를 받아옴.
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            StringBuilder result = new StringBuilder();

            // 처리된 이미지만큼 돌아가는 반복문.
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return null;
                }

//                String description = res.getTextAnnotationsList().get(0).getDescription();
//                description = description.replaceAll("\n", " ");
//                result.append(description);

                for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                    result.append(annotation.getDescription()).append(" , ");
                }
            }

            long endTime = System.currentTimeMillis();
            log.info("time flies {}", endTime-startTime);

            return result.toString();
        }
        catch (Exception exception) {
            return exception.getMessage();
        }
    }
}
