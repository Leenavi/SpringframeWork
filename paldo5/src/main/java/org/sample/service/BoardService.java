package org.sample.service;

import java.util.List;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.PostDTO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;

public interface BoardService {
	
	// 게시글 전체 목록 조회 (DTO 기반)
   public List<PostDTO> getPostList(Criteria cri);

   // 게시글 등록 (DTO 구성 요소 입력)
   public void register(PostDTO postDTO);

   // 게시글 1건 조회 (DTO 반환)
   public PostDTO get(Long boardid);

   // 게시글 수정
   public boolean modify(PostDTO postDTO);

   // 게시글 삭제
   public boolean remove(Long boardid);

   // 게시글 전체 목록 조회 (VO 기반, 필요 시 사용)
//   public List<PostDTO> getList(Criteria cri);

   // 전체 게시글 수 조회
   public int getTotal(Criteria cri);

   // 이미지 경로 등록
   public void registerImgPath(ImgPathVO imgpath);

   // 상품 등록
   public void registerProduct(ProductVO product);

   // 사용자 등록
   public void registerUser(UserVO user);

   // 이미지 목록 조회
   public List<ImgPathVO> getImgPaths(Long productid);




}  