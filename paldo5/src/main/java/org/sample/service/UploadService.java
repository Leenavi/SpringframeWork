package org.sample.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	// 파일 배열 받아서 경로 리스트로 반환
	List<String> uploadFiles(MultipartFile[] files);
}
