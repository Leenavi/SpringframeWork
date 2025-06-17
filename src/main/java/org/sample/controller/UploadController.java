package org.sample.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/upload")  // 모든 URL이 /upload로 시작됨
@Log4j
public class UploadController {

    // 업로드 폼 페이지 띄우기
    @GetMapping("/form")
    public void uploadForm() {
        log.info("uploadForm........");
    }

    // 업로드 처리
    @PostMapping("/formAction")
    public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
        String uploadFolder = "C:/upload";  // 실제 파일 저장 기본 경로

        // 오늘 날짜로 폴더 경로 생성 (예: 2025/05/19)
        String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        File uploadPath = new File(uploadFolder, datePath);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();  // 폴더 없으면 생성
        }

        List<String> imgPaths = new ArrayList<>();  // 업로드된 파일 경로 저장용

        for (MultipartFile multipartFile : uploadFile) {
            if (multipartFile.isEmpty()) continue;  // 빈 파일이면 패스

            String originalFileName = multipartFile.getOriginalFilename();
            if (originalFileName == null || originalFileName.trim().isEmpty()) {
                log.warn("파일명이 비어있거나 null입니다.");
                continue;
            }

            int dotIndex = originalFileName.lastIndexOf(".");
            if (dotIndex == -1) {
                log.warn("확장자가 없는 파일입니다: " + originalFileName);
                continue;
            }

            String ext = originalFileName.substring(dotIndex + 1).toLowerCase();

            // 허용할 확장자 검사
            if (!List.of("png", "jpg", "jpeg", "webp").contains(ext)) {
                log.warn("허용되지 않는 파일 확장자: " + ext);
                continue;  // 허용되지 않으면 저장하지 않고 다음 파일 처리
            }

            // UUID 붙여서 파일명 중복 방지
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "_" + originalFileName;

            File saveFile = new File(uploadPath, fileName);

            try {
                multipartFile.transferTo(saveFile);  // 실제 파일 저장
                log.info("Upload Success: " + fileName);

                // 웹에서 접근할 이미지 경로 생성 후 리스트에 저장
                String imgPath = "/upload/" + datePath + "/" + fileName;
                imgPaths.add(imgPath);

            } catch (Exception e) {
                log.error("파일 저장 실패: " + e.getMessage());
            }
        }

        model.addAttribute("imgPaths", imgPaths);  // JSP로 여러 이미지 경로 전달
    }
}
