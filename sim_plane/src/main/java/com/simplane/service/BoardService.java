package com.simplane.service;

import com.simplane.domain.BoardVO;

import java.util.List;

public interface BoardService {

    public BoardVO get(Long boardid);

    public List<BoardVO> getAll();
}
