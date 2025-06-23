package org.sample.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.domain.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
						"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
@WebAppConfiguration // 서버없이 테스트를 할 때 필요함
public class BoardControllerTests {

	@Autowired // 웹 관련 빈(생성된 객체) 관리
	private WebApplicationContext ctx;

	// 서버를 실행하지 않고도 HTTP 요청과 응답을 시뮬레이션하기 위한 도구
	private MockMvc mockMvc; 

	@Before // Spring MVC 애플리케이션에서 통합 테스트 수행
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testList() throws Exception {
		log.info(
			mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
			.andReturn()
			.getModelAndView()
			.getModelMap() // select(조회)할 때만 쓰는 .getModelMap()
		);
	}

	@Test
	public void testRegister() throws Exception {
		// 등록 폼에서 제목, 내용, 작성자 입력
		String resultPage = mockMvc.perform(MockMvcRequestBuilders
				.post("/board/register") // post 요청
				.param("title", "테스트 새글 제목") 
				.param("content", "테스트 새글 내용")
				.param("writer", "테스트 새글 작성자")
		).andReturn()
		.getModelAndView()
		.getViewName(); // select 아닌 경우 viewName을 확인

		log.info("==========>" + resultPage);
		assertEquals("redirect:/board/list", resultPage);  // 리다이렉트 확인
	}

	@Test
	public void testGet() throws Exception {
		BoardVO board = new BoardVO();
		log.info("BoardVO returned from mapper: " + board);
		log.info("board.getUserid(): " + board.getUserid());
	    log.info(
	        mockMvc.perform(MockMvcRequestBuilders
	            .get("/board/get")
	            .param("boardid", "1"))  // boardid 파라미터 추가
	        .andReturn()
	        .getModelAndView()
	        .getModelMap() // 게시글 조회 후 반환된 모델 데이터를 확인
	    );
	}

	@Test
	public void testDelete() throws Exception {
		// 특정 게시글 삭제
		String resultPage = mockMvc.perform(MockMvcRequestBuilders
				.post("/board/remove") 
				.param("bno", "10")
		).andReturn()
		.getModelAndView()
		.getViewName(); // 삭제 후 리다이렉트되는 페이지 확인

		log.info("==========>" + resultPage);
		assertEquals("redirect:/board/list", resultPage);  // 삭제 후 리다이렉트 확인
	}

	@Test
	public void testModify() throws Exception {
		// 특정 게시글 수정
		String resultPage = mockMvc.perform(MockMvcRequestBuilders
				.post("/board/modify") 
				.param("title", "수정 새글 제목") 
				.param("content", "수정 새글 내용")
				.param("writer", "수정 새글 작성자")
				.param("bno", "9")
		).andReturn()
		.getModelAndView()
		.getViewName(); // 수정 후 리다이렉트되는 페이지 확인

		log.info("==========>" + resultPage);
		assertEquals("redirect:/board/list", resultPage);  // 수정 후 리다이렉트 확인
	}
}
