package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {//페이징 처리에 필요한 계산 결과 (총 페이지 수, 시작/끝 페이지 번호, 이전/다음 여부 등)를 담는 역할

	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	//전체 레코드 개수
	private int total;
	//페이지 정보, 페이지당 레코드 개수
	private Criterial cri;
	
	public PageDTO(Criterial cri, int total) {
		this.cri = cri;
		this.total = total;
		
		//endPage
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		this.startPage = this.endPage - 9;
		//마지막 페이지
		int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
