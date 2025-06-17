package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}
	
	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();

		for(BoardVO vo : list)
			log.info(vo);
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = BoardVO.builder()
				.title("test title")
				.content("test content")
				.writer("test00")
				.build();
		
		mapper.insert(vo);
	}
	
	@Test
	public void testInsertKey() {
		BoardVO vo = BoardVO.builder()
				.title("test title")
				.content("test content")
				.writer("test00")
				.build();
		
		mapper.insertSelectKey(vo);
	}
	
	@Test
	public void testDelete() {
		int result = mapper.delete(7L);
		log.info("result >>>>>>>>>>>>>" + result); //1이 나오면 성공, 0이 나오면 실패
	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = BoardVO.builder()
				.title("수정제목")
				.content("수정내용")
				.writer("update00")
				.bno(6L)
				.build();
		int result = mapper.update(vo);
		log.info("result >>>>>>>>>>>>>" + result);
	}
	
	@Test
	public void testPaging() {
		List<BoardVO> list = mapper.getListWithPaging(new Criterial(1, 20));
		
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testSearch() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("T", "타이들"); //key=T, value=어버이날
		map.put("C", "afae");
		map.put("W", "홍길동");
		
		Map<String, Map<String, String>> outer = new HashMap<>();  //Map<String(key), Map<String, String>(value)>
		
		outer.put("map", map);
		List<BoardVO> list = mapper.searchTest(outer);
		
		log.info("----------------------------------------------------------------------------------------------------------------------------------");
		log.info(list);
	}
	
	@Test
	public void testSearch2() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("f");
		cri.setType("TC");
		
		mapper.getListWithPaging(cri).forEach(board -> log.info(board));
		
	}
	
	@Test
	public void testTotalCount() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("a");
		cri.setType("TC");
		
		log.info(mapper.getTotalCount(cri));
		
	}

}
