package org.zerock.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * create table tbl_board (
    bno number(10,0),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);
 */

/*
 * VO의 필드명 == SQL의 컬럼명 
 * 
 * 
 */
@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	
	private Long bno;
	//Long 사용 이유
	//오라클이나 MySQL에서 BIGINT, NUMBER() 등의 컬럼은 Java에서 Long으로 매핑해야 정확함
	//컬렉션(Generic) 사용 시 List<long>는 불가능하지만, List<Long>은 가능
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	
	private int replyCnt;
}

/*
 * BoardVO는 컨트롤러, 서비스, 매퍼를 **하나로 연결해주는 "데이터 통로"**야.
 * 각 계층이 공통으로 쓰기 때문에 import해서 사용하고,
 * 객체는 하나만 생성해서 주고받는 식으로 동작해.
 * 
 * 순수한 데이터 객체(POJO: Plain Old Java Object)로
 * 스프링에서 관리할 필요가 없기에 서블릿 컨텍스트에서 스캔하지 않아도 됨.
 * 순수한 데이터 객체(POJO: Plain Old Java Object)란?
	복잡한 기능 없이, 필드 + getter/setter + 생성자 정도만 있는 단순한 객체를 말해.
	보통 DB의 한 줄(row)이나 화면에 보여줄 데이터를 담기 위해 사용돼.
 *여기엔:
	비즈니스 로직 없음
	DB 연결 없음
	다른 객체 주입 없음
	어노테이션도 없음 (@Component, @Service 등)
	그냥 데이터를 "담았다 꺼내기만" 하는 용도야.
	그래서 이런 걸 “순수한 데이터 객체(VO/DTO/POJO)”라고 해.
	
 * 스프링에서 관리한다는 건
 * 객체를 스프링이 직접 만들고, 필요할 때 꺼내서 주입해주는 것
 * “new를 대신해주는 것” + “싱글톤으로 메모리에서 관리” + “자동으로 의존성 주입”
 * 
 * Controller, Service, Mapper 같은 여러 클래스들이 이 공통의 데이터 구조를 공유해야 하니까 BoardVO를 import해서 함께 사용
 */

