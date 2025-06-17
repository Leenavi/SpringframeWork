package org.sample.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
						"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
@WebAppConfiguration //서버없이 테스트를 할 때 필요함 
public class BoardControllerTests {
	
	@Autowired //웹 관련 빈(생성된 객체) 관리
	private WebApplicationContext ctx;
	
	//서버를 실행하지않고도 HTTP 요청과 응답을 시뮬레이션하기 위한 도구
	private MockMvc mockMvc; 

	@Before //Spring MVC 애플리케이션에서 통합 테스트 수행, 실제 서버를 실행하지 않고 
			//컨트롤러 동작을 테스트 가능 (서버없이 controller에서만 테스트하기위함이라서 복잡해짐 ..)
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap() //select(조회)할때만 쓰는거임 .getMomdelMap() 
		);
	}
	
	@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders
				.post("/board/register") //post요청으로 받아서
				.param("title", "테스트 새글 제목") 
				.param("content", "테스트 새글 내용")
				.param("writer", "테스트 새글 작성자")//값을 세개 넣어서 리턴
				).andReturn()
				.getModelAndView()
				.getViewName(); //select 아닌 3가지경우엔 이거를 써줌 
		
		log.info("==========>" + resultPage);
	}
	
	@Test
	public void testGet() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno","1"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
		);
	}
	

	@Test
	public void testDelete() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders
				.post("/board/remove") 
				.param("bno", "10")
				).andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info("==========>" + resultPage);
	}
	

	@Test
	public void testModify() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders
				.post("/board/modify") 
				.param("title", "수정 새글 제목") 
				.param("content", "수정 새글 내용")
				.param("writer", "수정 새글 작성자")
				.param("bno", "9")
				).andReturn()
				.getModelAndView()
				.getViewName(); 
		
		log.info("==========>" + resultPage);
	}

}













