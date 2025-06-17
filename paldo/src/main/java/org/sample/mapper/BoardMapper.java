package org.sample.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;

public interface BoardMapper {

	public List<BoardVO> getList();
	
	public BoardVO read(Long pno);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
	public List<BoardVO> getListByBrand(String brand);

	public int updateProduct(ProductVO product);

	public int updateBoard(BoardVO board);

	public ProductVO readProduct(Long productid);

	public UserVO readUser(Long userid);
	
	public UserVO getUser(Long userid);

	public List<ImgPathVO> getImageList(Long productid);

	public void deleteImages(Long productid);

	public void deleteProduct(Long productid);

	public int deleteBoard(Long boardid);

	public void insertUser(Long userid);

	public void insertProduct(String product);

	public void insertImgPath(ImgPathVO img);

	public void insertUser(UserVO user);

	public void insertProduct(ProductVO product);

	public void insertBoard(BoardVO board);

	
	
}
