package org.sample.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
    private Long boardid;      // 게시글 번호 (PK)
    private Long productid;    // 상품 ID (FK)
    private Long userid;       // 사용자 ID (FK)
    
    private String title;      // 게시글 제목
    private String status;     // 판매 상태 (판매중, 예약중 등)
    private Date regDate;      // 등록일

    // 추가된 조회 전용 필드들
    private String description;  // 상품 설명
    private Long price;          // 상품 가격
    private String condition;    // 상품 상태
    private String modelname;      // 모델명 (modelname)
    private String brand;        // 브랜드

    private String nickname;     // 작성자 닉네임
    private String pwd;          // 작성자 비밀번호

    private String imgPath;      // 이미지 경로 (대표 이미지)

	private UserVO user;

	private ProductVO product;
	private ImgPathVO img;
	
}
