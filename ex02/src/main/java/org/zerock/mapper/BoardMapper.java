package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

public interface BoardMapper {

	public List<BoardVO> getList();
	
	public BoardVO read(Long bno);
	
	public void insert(BoardVO board); //BoardVO의 매게변수일 경우 변수명은 상관 없음. 가독성을 위해 비슷하게 써줌.
	
	public void insertSelectKey(BoardVO board);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO> getListWithPaging(Criterial cri);
	
	public int getTotalCount(Criterial cri);
	
	public List<BoardVO> searchTest(Map<String, Map<String,String>> map);
	
}

/*	public void insert(BoardVO board);

*▶ VO 객체 전체를 넘김

▶ MyBatis는 board.getTitle(), board.getContent()처럼 내부 필드를 꺼내 쿼리에 씀

▶ XML에서는 #{title}, #{content}처럼 VO의 필드명을 직접 씀


*
*	public BoardVO read(Long bno);
*
*▶ 여기서 bno는 Long 타입 하나만 넘기는 단일 파라미터

▶ XML에서 #{bno}라고 써야 MyBatis가 전달된 Long bno 값을 인식함

▶ 이름이 다르면 동작 안 함!!
*/

