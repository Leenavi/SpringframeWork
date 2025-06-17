package org.sample.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UploadServiceImpl implements UploadService {
	
	private final String uploadFolder = "C:/upload";  // 파일 저장 루트 폴더

	@Override
	public List<String> uploadFiles(MultipartFile[] files) {
		
		List<String> imgPaths = new ArrayList<>();

        // 날짜별 폴더 생성
        String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        File uploadPath = new File(uploadFolder, datePath);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || originalFileName.trim().isEmpty()) {
                log.warn("파일명이 비어있습니다.");
                continue;
            }

            int dotIndex = originalFileName.lastIndexOf(".");
            if (dotIndex == -1) {
                log.warn("확장자가 없습니다: " + originalFileName);
                continue;
            }

            String ext = originalFileName.substring(dotIndex + 1).toLowerCase();
            if (!List.of("jpg", "jpeg", "png", "webp").contains(ext)) {
                log.warn("허용되지 않는 확장자입니다: " + ext);
                continue;
            }

            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + "_" + originalFileName;

            try {
                File saveFile = new File(uploadPath, savedFileName);
                file.transferTo(saveFile);

                String imgPath = "/upload/" + datePath + "/" + savedFileName;
                imgPaths.add(imgPath);

                log.info("업로드 성공: " + imgPath);

            } catch (Exception e) {
                log.error("파일 저장 실패: " + e.getMessage());
            }
        }

        return imgPaths;
    }
}
