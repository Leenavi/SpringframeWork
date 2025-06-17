package org.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sample.domain.ImgPathVO;

public interface ImgPathMapper {

	int insertImgPath(ImgPathVO imgPath);

    List<ImgPathVO> selectImgPathsByProductId(@Param("productid") int productid);

    int deleteImgById(@Param("imgid") int imgid);
}
