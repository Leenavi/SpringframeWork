package com.simplane.controller;

import com.simplane.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {

    private final BoardService service;

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam Long boardid, Model model) {
        log.info("get......modify..........");

        model.addAttribute("board", service.get(boardid));
    }
}
