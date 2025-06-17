package org.sample.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.PageDTO;
import org.sample.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {

    private final BoardService service;

    // 전체 게시글 목록
    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("list... " + cri);
        List<BoardVO> list = service.getList(cri);
        model.addAttribute("list", list);
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
    }

    // 게시글 작성 페이지 진입
    @GetMapping("/register")
    public void register() {
    }

    // 게시글 등록
    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
        log.info("register...");

        service.register(board);
        rttr.addFlashAttribute("result", board.getBoardid());

        return "redirect:/board/list";
    }

    // 게시글 상세 보기 및 수정 페이지 진입
    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("boardid") Long boardid, @ModelAttribute("cri") Criteria cri, Model model) {
        log.info("get or modify...");
        model.addAttribute("board", service.get(boardid));
    }

    // 게시글 삭제
    @PostMapping("/remove")
    public String remove(@RequestParam("boardid") Long boardid, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("remove...");

        if (service.remove(boardid)) {
            rttr.addFlashAttribute("result", "삭제 성공했습니다.");
        }

        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }

    // 게시글 수정
    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("modify...");

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "수정 성공했습니다.");
        }

        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }
}
