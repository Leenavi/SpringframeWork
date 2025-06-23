package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criterial {//클라이언트가 전달하는 요청 정보 (현재 페이지 번호, 페이지당 게시글 수 등)를 담는 역할

	private int pageNum;  //페이지
	private int amount;	  //페이지당 게시글 개수
	
	private String type; //검색 조건(제목, 내용, 작성자)
	private String keyword; //검색값
	
	public Criterial() {
		this(1, 10);
	}

	public Criterial(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {//검색 조건(T, W, C)를 배열로 만들어서 한 번에 처리 하기 위함.
		return type==null? new String[] {} : type.split("");
	}
}
