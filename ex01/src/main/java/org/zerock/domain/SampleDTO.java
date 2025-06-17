package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SampleDTO {

	private String name;
	private int age;
}


/*
@Builder => 사용시에 아래 두 어노테이션 써야함.

@AllArgsConstructor => 생성자 생성
public class SampleDTO (String name, int age) {

	this.name=name;
	this.age=age;
}

@noArgsConstructor  => 기본 생성자 생성
public class SampleDTO () {}
*/