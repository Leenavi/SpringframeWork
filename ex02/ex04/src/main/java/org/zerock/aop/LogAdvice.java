package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Aspect  //
public class LogAdvice {
	
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object longTime(ProceedingJoinPoint pjp) {
		
		long start = System.nanoTime();
		log.info(start);
		
		log.info("Target: "+ pjp.getTarget());
		log.info("Param: "+ Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed();  //함수 호출 처리
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.nanoTime();
		log.info(end);
		
		log.info("TIME: "+(end-start));
		return result;
	}
	
	
	
	
	
	
	
	
	
	
					// *: 반환타입, org~e* : 클래스, *(..) : 메소드, .. : 어떤 매개변수든 가능
/*	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore() {
	log.info("==============Before========================");
	}
					// *: 반환타입, org~e* : 클래스, *(..) : 메소드, .. : 어떤 매개변수든 가능
	@After("execution(* org.zerock.service.SampleService*.*(..))")
	public void logAfter() {
		log.info("=================After========================");
	}
	@AfterThrowing("execution(* org.zerock.service.SampleService*.*(..))")
	public void logAfterAfterThrowing() {
		log.info("=================AfterThrowing========================");
	}*/
}
