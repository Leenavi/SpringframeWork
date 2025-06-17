package org.sample.controller;

import java.util.Locale;

import org.sample.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
	
	private final BoardService boardService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		log.info("📥 HomeController 진입 - Locale: {}", locale);

		// 서비스에서 애플, 삼성 브랜드 중고폰 리스트 조회
		model.addAttribute("appleList", boardService.getAppleList());
		model.addAttribute("samsungList", boardService.getSamsungList());
		model.addAttribute("sonyList", boardService.getSonyList());
		model.addAttribute("otherList", boardService.getOtherList());

		// 메인 페이지 뷰 반환 (→ /WEB-INF/views/main.jsp)
		return "home";
		
		// return "redirect:/board/list"; 
		// get방식인데 redirect를 한 이유? 
		// 초기 진입 URL(/)로 접속한 사용자를 다른 페이지로 자동으로 보내기 위해서
	}
}
