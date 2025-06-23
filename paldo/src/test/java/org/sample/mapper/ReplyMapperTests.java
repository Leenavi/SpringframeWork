package org.sample.mapper;
/*package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.domain.Criterial;
import org.sample.domain.ReplyVO;
import org.sample.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper mapper;

	
	private Long[] bnoArr = {
			1835110L,1835109L,1835108L,
			1835107L,1835106L
	};
	
	
	@Test
	public void testCreate() {
		IntStream.range(1, 10)
		.forEach(i-> {
			ReplyVO vo = ReplyVO.builder()
					.bno(bnoArr[i%5])
					.reply("댓글 테스트"+i)
					.replyer("replyer"+i)
					.build();
		
			mapper.insert(vo);
		});
	}
	
	@Test 
	public void testRead() {
		log.info(mapper.read(1L));
	}

	@Test 
	public void testDelete() {
		log.info(mapper.delete(9L));
	}

	@Test 
	public void testUpdate() {
		
		ReplyVO vo = ReplyVO.builder()
				.reply("댓글 수정내용")
				.rno(8L)
				.build();
		
		log.info(
				mapper.update(vo)
				);
	}
	
	
	@Test
	public void testGetList() {
		Criterial cri =  new Criterial();
		Long bno = 1835108L;
		
		mapper.getListWithPaging(cri, bno)
			.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testList2() {
		Criterial cri = new Criterial(2,3);
		
		
		mapper.getListWithPaging(cri, 1835108L)
		.forEach(list-> log.info(list));
	}
	
}*/
