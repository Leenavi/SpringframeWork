package org.sample.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

    private int startPage;
    private int endPage;
    private boolean prev, next;
    
    //전체 레코드 개수
    private int total;
    //페이지 정보, 페이지당 레코드 개수
    private Criteria cri;
    
    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        // 전체 페이지 수 계산
        int realEnd = (int) Math.ceil(total * 1.0 / cri.getAmount());
        
        // 끝 페이지 계산
        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        
        // 시작 페이지 계산
        this.startPage = this.endPage - 9;

        // 마지막 페이지가 realEnd보다 적을 경우 끝 페이지 설정
        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }
        
        // prev와 next 버튼의 상태를 설정
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
        
        // 데이터가 없는 경우, next를 false로 설정하여 빈 페이지 링크를 숨김
        if (this.startPage > realEnd) {
            this.next = false;
        }
    }
}
