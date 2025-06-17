package org.zerock.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void test() {
		log.info("boardRepository : "+boardRepository);
	}
	
	@Test
	public void selectAlltest() {
		List<BoardVO> list = boardRepository.selectAllBoards();
		
		for(BoardVO vo : list)
			log.info(vo);
	}
	
	@Test
	public void selectOneByNumTest() {
		BoardVO vo = boardRepository.selectOneByNum(3);
		log.info("vo : "+vo);
	}
	
	@Test
	public void insertBoardTest() {
		BoardVO vo = new BoardVO();
		
			vo.setName("까미");
			vo.setPass("1234");
			vo.setEmail("dd");
			vo.setTitle("dog");
			vo.setContent("mung");
			
			boardRepository.insertBoard(vo);
	}
	
	@Test
	public void updateBoardTest() {
		BoardVO vo = new BoardVO();
		
			vo.setName("까미");
			vo.setPass("1234");
			vo.setEmail("dd@dd.ddd");
			vo.setTitle("dog");
			vo.setContent("멍멍멍");
			vo.setNum(41);
			
			boardRepository.updateBoard(vo);
	}
	
	@Test
	public void deleteBoardTest() {
			
			boardRepository.deleteBoard(45);
			boardRepository.deleteBoard(43);
			boardRepository.deleteBoard(42);
			boardRepository.deleteBoard(41);
	}
	
	@Test
	public void updateReadCountTest() {
			
			boardRepository.updateReadCount(22);
	}

}
