package org.zerock.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")  //어떤 파일에서 읽어올지
@Log4j  //sysout 대신 쓰는 것
public class SampleTests {
	
	@Autowired  //@Autowired=>root-context.xml 내의 관련 클래스를 찾아서 알아서 참조하도록 해줌.
	private Restaurant restaurant;
	
	@Test
	public void testRest() {
		log.info("------------------------------------");
		log.info("restaurant : "+restaurant);
		restaurant.sample();
	}
	
}
