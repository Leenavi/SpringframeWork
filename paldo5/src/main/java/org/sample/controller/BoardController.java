package org.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.PageDTO;
import org.sample.domain.PostDTO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;
import org.sample.service.BoardService;
import org.sample.service.UploadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {

    private final BoardService boardService;
    private final UploadService uploadService;

    // 게시글 목록 조회
    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("📄 list: " + cri);

        List<PostDTO> postList = boardService.getPostList(cri);
        model.addAttribute("list", postList);
        model.addAttribute("pageMaker", new PageDTO(cri, boardService.getTotal(cri)));
    }
    
    // 게시글 등록 페이지 진입
    @GetMapping("/register")
    public void register() {
        // 등록 폼 이동
    }

 // 게시글 등록 + 파일 업로드
    @PostMapping("/register")
    public String register(@ModelAttribute PostDTO postDTO,
                           @RequestParam("uploadFiles") MultipartFile[] uploadFiles,
                           RedirectAttributes rttr) {

        log.info("📝 register...");

        // 업로드된 파일 저장
        List<String> savedPaths = uploadService.uploadFiles(uploadFiles);

        // ImgPathVO 리스트 구성 후 postDTO에 설정
        List<ImgPathVO> imgList = new ArrayList<>();
        for (String path : savedPaths) {
            ImgPathVO img = ImgPathVO.builder()
                                     .imgPath(path)
                                     .build();
            imgList.add(img);
        }
        postDTO.setImgPaths(imgList);

        // 게시글 등록 처리
        boardService.register(postDTO);
        rttr.addFlashAttribute("result", postDTO.getBoard().getBoardid());

        return "redirect:/board/list";
    }

    // 게시글 상세조회 및 수정페이지 진입
    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("boardid") Long boardid,
                    @ModelAttribute("cri") Criteria cri,
                    Model model) {

        log.info("🔍 get or modify for boardid=" + boardid);
        PostDTO dto = boardService.get(boardid);
        model.addAttribute("dto", dto);
    }

    // 게시글 삭제
    @PostMapping("/remove")
    public String remove(@RequestParam("boardid") Long boardid,
                         @ModelAttribute("cri") Criteria cri,
                         RedirectAttributes rttr) {

        log.info("❌ remove boardid=" + boardid);
        boolean removed = boardService.remove(boardid);

        rttr.addFlashAttribute("result", removed ? "삭제 성공했습니다." : "삭제에 실패했습니다.");
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }

    // 게시글 수정
    @PostMapping("/modify")
    public String modify(@ModelAttribute PostDTO postDTO,
                         @RequestParam("uploadFiles") MultipartFile[] uploadFiles,
                         @ModelAttribute("cri") Criteria cri,
                         RedirectAttributes rttr) {
    	
    	if (postDTO == null || postDTO.getBoard() == null) {
    	    log.error("postDTO 또는 board가 null입니다.");
    	    rttr.addFlashAttribute("result", "잘못된 요청입니다.");
    	    return "redirect:/board/list";
    	}

        log.info("✏️ modify boardid=" + postDTO.getBoard().getBoardid());

        // 파일 업로드 처리
        List<String> savedPaths = uploadService.uploadFiles(uploadFiles);
        List<ImgPathVO> imgList = new ArrayList<>();
        for (String path : savedPaths) {
            ImgPathVO img = ImgPathVO.builder()
                                     .imgPath(path)
                                     .build();
            imgList.add(img);
        }
        postDTO.setImgPaths(imgList);

        boolean modified = boardService.modify(postDTO);

        rttr.addFlashAttribute("result", modified ? "수정 성공했습니다." : "수정 실패했습니다.");
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }

}











