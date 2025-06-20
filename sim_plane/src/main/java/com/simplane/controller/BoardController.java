package com.simplane.controller;

import com.simplane.domain.BoardVO;
import com.simplane.domain.Criteria;
import com.simplane.domain.PageDTO;
import com.simplane.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {

    private final BoardService service;

    //목록 읽어오기
    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("list================");

        List<BoardVO> list = service.getAll(cri);
        model.addAttribute("list", list);

        int total = service.getTotal(cri);

        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }
    
    //단 건 읽어오기
    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam Long boardid, Model model) {
        log.info("get......modify..........");

        model.addAttribute("board", service.get(boardid));
    }
}
