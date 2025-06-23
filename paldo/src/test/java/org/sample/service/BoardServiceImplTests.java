/*package org.sample.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.domain.BoardVO;
import org.sample.domain.Criterial;
import org.sample.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
						"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class BoardServiceImplTests {

	//service통해서 db저장
	@Autowired
	private BoardService service;
	
	@Test
	public void testRegister() {
		BoardVO vo = BoardVO.builder()
				.title("서비스 제목")
				.content("서비스 내용")
				.writer("service00")
				.build();
		
		service.register(vo); //boardService에 register에 vo값주겠다.
	}

	@Test
	public void testGetList() {	//람다식
		service.getList(new Criterial()).forEach(board -> log.info(board)); //전체데이터 6번 반복해서 가져와서 값을 찍어줌
	}
}
*/