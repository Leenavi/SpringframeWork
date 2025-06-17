package org.sample.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.PostDTO;

public interface BoardMapper {

	public void insertBoard(BoardVO board);
	public void insertSelectKeyBoard(BoardVO board);
	public BoardVO readBoard(Long boardid);
	public int updateBoard(PostDTO postDTO);
	public int deleteBoard(@Param("boardid") Long boardid);
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int countTotal(Criteria cri);
	public List<BoardVO> getList(Criteria cri);
	
}
