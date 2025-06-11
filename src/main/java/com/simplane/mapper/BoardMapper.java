package com.simplane.mapper;

import com.simplane.domain.BoardVO;

public interface BoardMapper {

    public BoardVO read(Long boardid);

    public void create(BoardVO board);

    public int update(BoardVO board);

    public int delete(Long boardid);
}
