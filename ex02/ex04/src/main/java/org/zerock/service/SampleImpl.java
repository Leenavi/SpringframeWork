package org.zerock.service;

import org.springframework.stereotype.Service;

@Service
public class SampleImpl implements SampleService{

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer doMul(Integer n1, Integer n2) {
		System.out.println("========doMul==========");
		//sysout 처리 후 LogAdvice의 after가 끝날 때까지 기다렸다가 return 실행
		return n1*n2;
	}
}
