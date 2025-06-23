package org.sample.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.PageDTO;
import org.sample.domain.PostDTO;
import org.sample.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor //생성자 자동 주입
@Log4j
public class BoardController {
    
    private final BoardService service;
    
    // 게시글 리스트 목록 조회 
    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("list.................." + cri);

        //게시글 목록 조회 
        List<BoardVO> list = service.getList(cri);
        model.addAttribute("list", list); // jsp에 전달 

        //페이징 정보 생성 및 전달 
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
    }
    

    // 게시글 등록 폼 페이지 이동 
    @GetMapping("/register")
        public void register() {
            // 등록 폼으로 이동
        }

    // 게시글 등록 처리 (이미지 업로드 + 게시글 저장 post로 처리)
     @PostMapping("/register")
     public String register(
         @RequestParam("mainImage") MultipartFile mainImage,
         HttpServletRequest request,
         BoardVO board,
         RedirectAttributes rttr) {

        // 1. 이미지 업로드 처리
         if (!mainImage.isEmpty()) {
            //각자 경로에 맞게 수정 프로젝트 내부 폴더에 넣어야 서버가 읽어 올수 있음
            String uploadFolder = request.getServletContext().getRealPath("/resources/images/upload");
             String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
             File uploadPath = new File(uploadFolder, datePath);
             if (!uploadPath.exists()) uploadPath.mkdirs();

             String originalFileName = mainImage.getOriginalFilename();
             String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
             String uuid = UUID.randomUUID().toString();
             String fileName = uuid + "_" + originalFileName;
             File saveFile = new File(uploadPath, fileName);

             try { // 파일 저장
                 mainImage.transferTo(saveFile);
                 // 이미지 저장 경로를 DB에 저장할 수 있게 BoardVO에 세팅

    //이부분 images/뒤에 upload/추가
                 String imgPath = "/resources/images/upload/" + datePath + "/" + fileName;
                 board.setImgPath(imgPath); // 필드명은 DTO에 맞게 조정
             } catch (IOException e) {
                 log.error("파일 업로드 실패", e);
                 // 파일 업로드 실패 시 예외 처리 가능 
             }
         }

        // 게시글 등록 처리 
        log.info("register..........." + board);
        service.register(board); // DB 저장 

        rttr.addFlashAttribute("result", board.getBoardid()); 
        return "redirect:/board/list";
    }
    
    
    // 게시글 상세보기 및 수정페이지 이동 
    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("boardid") Long boardid,
                    @ModelAttribute("cri") Criteria cri,
                    Model model) {
        log.info("get...modify....... boardid = " + boardid);

        PostDTO dto = service.get(boardid); // 게시글 ID로 상세 정보 조회 
        model.addAttribute("dto", dto); // 모델에 dto 이름으로 데이터 전달 
    }


    // 게시글 삭제 처리 
    @PostMapping("/remove")
    public String remove(@RequestParam("board.boardid") Long boardid, RedirectAttributes rttr) {
       
       boolean result = service.remove(boardid); // 삭제 요청 처리 

       // 결과에 따라 메세지 전달 
        if (result) {
            rttr.addFlashAttribute("result", "게시글이 성공적으로 삭제되었습니다.");
        } else {
            rttr.addFlashAttribute("result", "게시글 삭제에 실패했습니다.");
        }

        return "redirect:/board/list"; // 게시판 목록 페이지로 리다이렉트
    }

    // 게시글 수정 처리 
    @PostMapping("/modify")
    public String modify(PostDTO dto, 
                   @ModelAttribute("cri") Criteria cri, 
                   RedirectAttributes rttr) {
        log.info("modify..........");

        // 수정 성공 시 flash 메세지 저장 
        if (service.modify(dto)) {
            rttr.addFlashAttribute("result", "수정 성공했습니다.");
        }
        // 페이징 정보를 다시 넘겨줌 (검색 조건 유지)
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list"; //목록으로 이동
    }
    
    
}
