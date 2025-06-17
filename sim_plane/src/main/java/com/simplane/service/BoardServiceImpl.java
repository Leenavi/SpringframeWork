package com.simplane.service;

import com.simplane.domain.BoardVO;
import com.simplane.mapper.BoardMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
@RequiredArgsConstructor //생성자 주입
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    @Override
    public BoardVO get(Long boardid) {
        log.info("get.........."+boardid);
        return mapper.read(boardid);
    }

    @Override
    public List<BoardVO> getAll() {
        log.info("getAll..........");
        return mapper.readAll();
    }
}
