package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

public interface BoardService {
	
	public void register(BoardVO board); //등록
	
	public BoardVO get(Long bno); //1 건 조회
	
	public boolean modify(BoardVO board);  //수정
	
	public boolean remove(Long bno);  //삭제
	
	public List<BoardVO> getList(Criterial cri);  //전체 데이터 조회
	
	public int getTotal(Criterial cri); //전체 레코드 조회 or 조건에 맞는 데이터 조회
}
