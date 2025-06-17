package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criterial {

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
	
	public String[] getTypeArr() {
		return type==null? new String[] {} : type.split("");
	}
}
