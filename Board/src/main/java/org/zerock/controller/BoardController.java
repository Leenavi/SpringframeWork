package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.BoardVO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
/*
 /board/boardList
 /board/view
 /board/
  
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {
	
	@Autowired
	private final BoardService boardService;
	
	@GetMapping({"/" ,"/boardList"})
	public String boardList(Model model) {
		
		List<BoardVO> list = boardService.selectListBoard();
		
		model.addAttribute("boardList", list);
		
		return "boardList";
	}
	@GetMapping("/register")
	public String register() {
		return "boardRegister";
	}
	
	@PostMapping("/register")
	public String insertBoard(BoardVO bVo) {  //폼 데이터가 setter를 통해 자동 매핑됨 (데이터 바인딩)
		boardService.insertBoard(bVo);		  //스프링이 객체를 만들어서 주입함 (DI)
		return "redirect:/board/boardList";
	}
	/* 이렇게 BoardVO 안에 값이 다 채워진 후에
	 * insertBoard(BoardVO bVo) 메서드를 호출해주는 거지!*/
	
	@GetMapping("/view")//@RequestParam("num")에서 ("") 빼도 되긴 함. 그냥 아예 빼도 됨.
	public String viewBoard(@RequestParam("num") int num, Model model) {
		BoardVO bVo = boardService.selectOneByNum(num);
		boardService.updateReadCount(num);
		model.addAttribute("board", bVo);
		return "boardView";
	}
	@GetMapping("/check")
	public String checkGet(@RequestParam int num, Model model) {
		model.addAttribute("num", num);
		return "checkBoard";
	}
	
	@PostMapping("/check")
	public String CheckPost(@RequestParam int num, @RequestParam String pass,
			Model model) {
		//서비스 호출해서 true(비밀번호 맞음), false(비밀번호 틀림) 반환받는다.
		boolean check = boardService.checkPassword(num, pass);
		
		if(check) {
			//비밀번호 맞음
			model.addAttribute("num", num);
			return "checkSuccess";
		}else {
			//비밀번호 틀렸다.
			model.addAttribute("message", "비밀번호가 틀립니다.");
			model.addAttribute("num", num);
			return "checkBoard";
		}
	}
	@PostMapping("/check2") 
	public 	String checkPost2(@RequestParam("num") int num,@RequestParam String pass, Model model) {
		boolean check2 = boardService.checkPassword2(num, pass);
		
		if(check2) {
			//비밀번호 맞음
			model.addAttribute("num", num);
			return "checkSuccess";
		}else {
			//비밀번호 틀렸다.
			model.addAttribute("message", "비밀번호가 틀립니다.");
			model.addAttribute("num", num);
			return "checkBoard";
		}
	}
	@GetMapping("/delete")
	public String deleteGet(@RequestParam int num) {
		boardService.deleteBoard(num);
		return "redirect:/board/boardList";
	}
	@GetMapping("/update")
	public String updateBoard(@RequestParam("num") int num, Model model) {
		BoardVO bVo = boardService.selectOneByNum(num);
		model.addAttribute("board", bVo);
		return "boardUpdate";
	}
	@PostMapping("/update")
	public String updateBoard(BoardVO bVo) {
		boardService.updateBoard(bVo);
		return "redirect:/board/view?num="+bVo.getNum();
	}
	
}
