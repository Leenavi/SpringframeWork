package org.zerock.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
@RequiredArgsConstructor
public class BoardController {
	private final BoardService service;
	
	@GetMapping("/list")
	public void list(Criterial cri, Model model) {
		log.info("list................."+cri);
		List<BoardVO> list = service.getList(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
	}
	
	
	/*
	 * @GetMapping("/register") //get으로 요청이 들어오면 register.jsp 화면을 보여줘라 public void
	 * register() {}
	 */
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register.......................");
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criterial cri, Model model) {
		log.info("get....modify.............");
		model.addAttribute("board", service.get(bno));
//		model.addAttribute("cri", cri);
	}
	
	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno,  @ModelAttribute("cri") Criterial cri,
			RedirectAttributes rttr, String writer, Authentication au) {
		log.info("remove..................." + bno);
		log.info("remove...................writer" + writer);
		log.info("remove...................principal.username" + au.getName());
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "삭제 성공했습니다.");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("type", cri.getType());
		return "redirect:/board/list";
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criterial cri, RedirectAttributes rttr) {
		log.info("modify......................");
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "수정 성공했습니다.");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("type", cri.getType());
		return "redirect:/board/list";
	}
}
