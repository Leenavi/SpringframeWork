package org.sample.controller;

import java.util.List;
import java.util.Locale;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

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
    public String home(Locale locale, Model model, Criteria cri)  {
        log.info("📥 HomeController 진입 - Locale: {}", locale);

        // 서비스에서 애플, 삼성 브랜드 중고폰 리스트 조회
        model.addAttribute("appleList", boardService.getAppleList());
        model.addAttribute("samsungList", boardService.getSamsungList());
        model.addAttribute("sonyList", boardService.getSonyList());
        model.addAttribute("otherList", boardService.getOtherList());
       
        // 메인 페이지 뷰 반환 (→ /WEB-INF/views/main.jsp)
        return "home";
    }
    
 
    
}
