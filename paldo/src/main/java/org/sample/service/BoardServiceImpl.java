package org.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.PostDTO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;
import org.sample.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor

public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    @Override
    public void register(BoardVO board) {
    	
      // 1. 사용자 등록 (userid 생성)
      mapper.insertUser(board.getUser());

      // 2. 상품 등록 (productid 생성)
      mapper.insertProduct(board.getProduct());

      // 3. 이미지 등록 (imgid 생성, productid 매핑)
      ImgPathVO img = new ImgPathVO();
      img.setProductid(board.getProduct().getProductid());
      img.setImgPath(board.getImgPath());
      mapper.insertImgPath(img);

      // 4. 게시글 등록 (boardid 생성, userid, productid 연결)
      board.setUserid(board.getUser().getUserid());
      board.setProductid(board.getProduct().getProductid());
      mapper.insertBoard(board);
    
  }

    @Override
    public PostDTO get(Long boardid) {
        log.info("get PostDTO......" + boardid);

        BoardVO board = mapper.read(boardid);

        // 연관된 상품 정보
        ProductVO product = mapper.readProduct(board.getProductid());

        // 사용자 정보 디버깅 추가
        log.info("Fetching user with userid: " + board.getUserid());  // userid 값 확인
        UserVO user = mapper.readUser(board.getUserid());

        // 사용자 정보가 null인 경우 처리
        if (user == null) {
            log.error("User not found for userid: " + board.getUserid());
            board.setNickname("Unknown"); // 기본값 설정 또는 예외 처리
        } else {
            board.setNickname(user.getNickname());
        }

        // 이미지 목록
        List<ImgPathVO> imgPaths = mapper.getImageList(board.getProductid());

        // 모든 데이터를 DTO로 묶어서 반환
        return new PostDTO(board, user, product, imgPaths);
    }

    @Override
    	public boolean modify(PostDTO dto) {
    	    int productUpdated = mapper.updateProduct(dto.getProduct());
    	    int boardUpdated = mapper.updateBoard(dto.getBoard());
    	    return productUpdated == 1 && boardUpdated == 1;
    	}
    

    @Transactional
    @Override
    public boolean remove(Long boardid) {
        try {
            // 1. 먼저 이미지 경로 삭제
            BoardVO board = mapper.read(boardid); // 게시글 정보를 먼저 조회하여 productid를 가져옴
            Long productid = board.getProductid();

            // 2. 이미지 경로 삭제
            mapper.deleteImages(productid);

            
            // 4. 게시글 삭제
            int rowsAffected = mapper.deleteBoard(boardid);

            // 3. 상품 삭제
            mapper.deleteProduct(productid);

            // 삭제 성공 여부 반환
            return rowsAffected == 1;
        } catch (Exception e) {
            log.error("삭제 실패: " + e.getMessage(), e);
            return false;
        }
    }


    @Override
    public List<BoardVO> getList(Criteria cri) {
        log.info("getList.....");
        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("getTotal...............");
        return mapper.getTotalCount(cri);
    }

    @Override
	public List<BoardVO> getAppleList() {
		
    	 return mapper.getListByBrand("Apple");  // brand와 cri 둘 다 전달
    }
	
	

	@Override
	public List<BoardVO> getSamsungList() {
		
		return mapper.getListByBrand("Samsung");
	}

	@Override
	public List<BoardVO> getSonyList() {
		
		return mapper.getListByBrand("Sony");
	}

	@Override
	public List<BoardVO> getOtherList() {
		
		return mapper.getListByBrand("LG");
	}
	
	


}
