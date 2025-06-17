package org.sample.domain;

import java.util.Date;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

	private Long boardid;
	private Long productid;
	private Long userid;
	private String status;
	private Date regDate;
	private String title;
	
	private UserVO user;           // 조인 없이 가져올 사용자 VO(리스트용)
	private ProductVO product;     // 조인 없이 가져올 상품 VO(리스트용)
    private List<ImgPathVO> imgPaths;       // 이미지 경로 리스트
}
