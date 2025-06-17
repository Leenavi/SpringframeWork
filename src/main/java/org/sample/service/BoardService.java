package org.sample.service;

import java.util.List;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.mapper.BoardMapper;
import org.sample.mapper.ImgPathMapper;
import org.sample.mapper.ProductMapper;
import org.sample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface BoardService {

	@Autowired
    public BoardMapper boardMapper;

    @Autowired
    public ProductMapper productMapper;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public ImgPathMapper imgPathMapper;
	
	
    // 게시글 등록
    public void register(BoardVO board);

    // 게시글 1건 조회 (기본키 pno 사용)
    public BoardVO get(Long pno);

    // 게시글 수정
    public boolean modify(BoardVO board);

    // 게시글 삭제 (기본키 pno 사용)
    public boolean remove(Long pno);

    // 검색/페이징 포함 전체 리스트 조회
    public List<BoardVO> getList(Criteria cri);

    // 검색/조건 포함 전체 게시글 수 조회
    public int getTotal(Criteria cri);
    
    // 브랜드별로 게시글 구분하여 조회
    public List<BoardVO> getAppleList();

    public List<BoardVO> getSamsungList();

    public List<BoardVO> getSonyList();
    
    public List<BoardVO> getOtherList();
}