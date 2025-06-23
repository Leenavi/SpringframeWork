package org.sample.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private BoardVO board;         // 게시글 정보 (boardid, title, status, regdate, productid, userid)
    private UserVO user;           // 작성자 정보 (userid, nickname, email 등)
    private ProductVO product;     // 상품 정보 (productid, description, price, brand, condition, modelname)
    private List<ImgPathVO> imgPaths; // 이미지 정보 리스트 (imgid, productid, imgpath)
}
