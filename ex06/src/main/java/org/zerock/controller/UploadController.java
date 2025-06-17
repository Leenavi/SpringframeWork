package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form.............................");
	}
	
	@PostMapping("/uploadForm")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("----------------------------------------------------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File size : " + multipartFile.getSize());
									//C:\\upload\\c++3.PNG
			File savedfile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(savedfile);
			}catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		return str.replace("-", File.separator); // 윈도우 기준. "-" 기준으로 하위폴더 생성
	}
	
	private boolean checkImageType(File file) {
		
		try {
			log.info("--------------check imgType------------");

			log.info(file.toPath());

			String contentType = Files.probeContentType(file.toPath());
			log.info(contentType);
			log.info(contentType.startsWith("image"));
			log.info("---------------check imgType------------------");
			
			
			return contentType.startsWith("image");
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return false;
	}
												//형식을 알 수 없는 모든 종류의 파일에 사용할 수 있는 기본값 MIME
	@GetMapping(value = "/download", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		
		FileSystemResource resource = new FileSystemResource("c:\\upload\\" + fileName);
		
		String resourceName = resource.getFilename();
		log.info("resourceName >>"+ resourceName);
		
		String downloadName = resourceName.substring(resourceName.indexOf("_")+1);
		
		log.info("downloadName >>"+ downloadName);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(downloadName, "utf-8"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	@GetMapping("/display")
	@ResponseBody		  //byte A-> 01000001, a-> 01100001
	public ResponseEntity<byte[]> getFile(String fileName) {
		File file = new File("c:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
										//MIME 타입 --> image/jpg, application/pdf
			header.add("Content-Type", Files.probeContentType(file.toPath()));
										//본문(내용)
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
											//MIME타입, 상태코드
											header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;  //MIME타입을 포함한 바이너리 데이터로 응답(상태 코드 포함)
	}
	
	@PostMapping(value="/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		File file;
		
		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));
			file.delete();
			
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				
				log.info("largeFileName: "+ largeFileName);
				
				file = new File(largeFileName);
				
				file.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax...........");
	}
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile, Model model) {
		
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFolder();  //2025\\05\\27\\

				//C:\\upload\\2025\\05\\27\\c++3.PNG
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(uploadPath.exists() == false) { //폴더 생성
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : uploadFile) {
//			log.info("----------------------------------------------------------------------");
//			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
//			log.info("Upload File size : " + multipartFile.getSize());
			
			AttachFileDTO attachFileDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			attachFileDTO.setUuid(uuid.toString());
			attachFileDTO.setFileName(multipartFile.getOriginalFilename());  //원본 파일명
			attachFileDTO.setUploadPath(uploadFolderPath);
			
			try {
				File savedFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(savedFile);
				//썸네일 파일 생성
				if(checkImageType(savedFile)) {
					
					attachFileDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),
							thumbnail, 200, 200);
					
					thumbnail.close();  //stream 쓰고 항상 close 해야함.
				}//end if
				list.add(attachFileDTO);
			//end try	
			}catch (Exception e) {
				log.error(e.getMessage());
			}//end catch
		}//end for
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@PostMapping("/uploadAjaxAction_old")
	public @ResponseBody String uploadAjaxAction_old(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("----------------------------------------------------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File size : " + multipartFile.getSize());
									//C:\\upload\\c++3.PNG
			File savedfile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(savedfile);
			}catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return "success";
	}
}
