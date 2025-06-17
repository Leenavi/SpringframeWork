package org.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;

public interface BoardMapper {

	int insertBoard(BoardVO board);

    BoardVO selectBoardById(@Param("boardid") int boardid);

    int updateBoard(BoardVO board);

    int deleteBoard(@Param("boardid") int boardid);
	
}
