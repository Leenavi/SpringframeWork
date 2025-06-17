package org.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sample.domain.ImgPathVO;
import org.sample.domain.ProductVO;

public interface ImgPathMapper {
	
	public int insertImgPath(ImgPathVO imgPath);
	public int insertSelectKeyImgPath(ImgPathVO imgPath);
	public List<ImgPathVO> readImgPath(@Param("productid") Long productid);
	public int updateImgPath(ImgPathVO imgPath);
	public int deleteImgPath(@Param("imgid") Long imgid);
	public int getImgListById(@Param("productid") Long productid);
}
