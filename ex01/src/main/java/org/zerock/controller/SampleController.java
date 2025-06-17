package org.zerock.controller;


import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

	@RequestMapping("/")
	public void basic() {  //void 쓸 경우 view를 직접 찾아감./WEB-INF/views/sample.jsp
		log.info("basic............................");
	}
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {  ///WEB-INF/views/sample/basic.jsp
		log.info("basic get...................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {  ///WEB-INF/views/sample/basicOnlyGet.jsp
		log.info("basic get only get.....................");
	}
	
	@GetMapping("ex01")
	public String ex01(SampleDTO dto) {
		log.info(""+dto);
		
		return "ex01";
	}
	
	@GetMapping("ex02")
	public String ex02(String name, int age, Model model) {
//		name="eeeeee";
//		age=888;
		
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "ex02";
	}
	
/*	@GetMapping("ex02")
	public String ex02(Model model) {
	
		model.addAttribute("name", "eee");
		model.addAttribute("age", 99);
		
		return "ex02";
	}*/
/*	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class,
				 new CustomDateEditor(dateFormat, false));
	}*/
	
	@GetMapping("ex03")
	public String ex03(TodoDTO tododto) {
		log.info(""+tododto);
		
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/rttr")
	public String rttr(SampleDTO dto, RedirectAttributes rttr) {
		rttr.addFlashAttribute("sampleDTO", dto);
		return "redirect:/sample/ex04";
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06....................");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("hong");
		
		return dto;
//		return SampleDTO dto = SampleDTO.builder().name("park").age(29).build();
//		객체 생성하자마자 리턴시키는 것
	}
	/*
	 * @ResponseBody
		클라이언트에게 리턴 데이터를 그대로 응답 본문으로 보낼 때 사용합니다.
		
		보통 컨트롤러에서 return 한 값은 뷰 이름으로 처리되는데,
		
		@ResponseBody를 붙이면 그냥 문자열 또는 객체를 JSON으로 변환해서 직접 출력합니다.
	 */
	@GetMapping("/hello")
	@ResponseBody
	public String sayHello() {
	    return "Hello"; // 브라우저에 그냥 "Hello" 출력
	}
/*	@GetMapping("/user")
	@ResponseBody
	public User getUser() {
	    return new User("홍길동", 30); // JSON으로 응답됨: {"name":"홍길동", "age":30}
	}*/
	
	@GetMapping("/ex06_1")
	public String ex06_1(@RequestBody SampleDTO dto) {
		log.info("/ex06_1....................");
		log.info(dto);
		
		return "ex06_1";
	}
	/*
	 * @RequestBody
		클라이언트가 보낸 JSON 데이터를 자바 객체로 바인딩할 때 사용합니다.
	 */
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("/ex07.................");
		
		// {"name": "홍길동"}
		String msg = "{\"name\":\"홍길동\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		
		return new ResponseEntity<String>(msg, headers, HttpStatus.OK);
	}
}
