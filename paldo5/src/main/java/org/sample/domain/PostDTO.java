package org.sample.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data // getter, setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class PostDTO {

    private BoardVO board;           // 게시글 정보
    private UserVO user;             // 작성자 정보
    private ProductVO product;       // 상품 정보
    private List<ImgPathVO> imgPaths; // 이미지 경로들
}

