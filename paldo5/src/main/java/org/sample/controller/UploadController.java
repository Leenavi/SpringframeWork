package org.sample.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.sample.domain.ImgPathVO;
import org.sample.domain.PostDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

    // 게시글 등록 시 파일 업로드 처리
    @PostMapping("/post/upload")
    public String uploadFiles(MultipartFile[] uploadFile, PostDTO postDTO, Model model) {
        // ⚠️ 개발 환경 기준: 실제 경로로 변환
        String uploadFolder = "C:/upload";  // 이미지 저장 경로
        											
        
        // 날짜별 폴더 구조 생성 (예: 2025/05/19)
        String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        File uploadPath = new File(uploadFolder, datePath);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();  // 경로(폴더)가 없으면 생성
        }

        List<ImgPathVO> imgPaths = new ArrayList<>();

        for (MultipartFile multipartFile : uploadFile) {
            if (multipartFile.isEmpty()) continue;

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

            if (!List.of("png", "jpg", "jpeg", "webp", "gif").contains(ext)) {
                log.warn("허용되지 않는 파일 확장자: " + ext);
                continue;
            }

            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "_" + originalFileName;

            File saveFile = new File(uploadPath, fileName);

            try {
                multipartFile.transferTo(saveFile);
                log.info("Upload Success: " + fileName);

                // 썸네일 생성
                if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("webp")) {
                    createThumbnail(saveFile, uploadPath, fileName, ext);
                }

                // 웹에서 접근 가능한 경로 (JSP 등에서 이미지 출력 시 사용)
                String imgPath = "/resources/images/" + datePath + "/" + fileName;

                ImgPathVO imgVO = ImgPathVO.builder()
                        .imgPath(imgPath)
                        .build();
                imgPaths.add(imgVO);

            } catch (Exception e) {
                log.error("파일 저장 실패: " + e.getMessage());
            }
        }

        model.addAttribute("imgPaths", imgPaths);

        return "redirect:/board/list";  // 게시판 목록으로 이동
    }

    // 썸네일 생성 메서드
    private void createThumbnail(File originalFile, File uploadPath, String fileName, String ext) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalFile);
        int width = originalImage.getWidth() / 2;
        int height = originalImage.getHeight() / 2;
        BufferedImage thumbnailImage = new BufferedImage(width, height, originalImage.getType());

        thumbnailImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);

        File thumbnailFile = new File(uploadPath, "thumb_" + fileName);
        ImageIO.write(thumbnailImage, ext, thumbnailFile);
        log.info("Thumbnail Created: " + thumbnailFile.getName());
    }
}

