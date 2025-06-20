package com.simplane.service;

import com.simplane.domain.BoardVO;
import com.simplane.domain.Criteria;

import java.util.List;

public interface BoardService {

    public BoardVO get(Long boardid);

    public List<BoardVO> getAll(Criteria cri);

    public int getTotal(Criteria cri);
}
