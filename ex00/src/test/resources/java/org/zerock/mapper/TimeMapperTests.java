package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.BoardVO;
import org.zerock.persistance.DataSourceTests;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {

	@Autowired
	private TimeMapper timeMapper;
	
	@Test
	public void testGetTime() {
		log.info("--------------------------------------------------");
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
	}
	
	@Test
	public void testGetTime2() {
		log.info("--------------------------------------------------");
		log.info(timeMapper.getTime2());
	}
	
	@Test
	public void testAllList() {
		List<BoardVO> list = timeMapper.selectAllList();
		
		for(BoardVO vo : list)
			log.info(vo);
	}
	
	@Test
	public void testSelectOne() {
		log.info(timeMapper.selectOneByNum(5));
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		
		vo.setName("까미");
		vo.setPass("1234");
		vo.setEmail("ddddddddddddddddddddddd");
		vo.setTitle("강아지");
		vo.setContent("강아지입니다");
		
		timeMapper.insertBoard(vo);
	}
}
