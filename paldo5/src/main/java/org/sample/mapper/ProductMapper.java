package org.sample.mapper;

import org.apache.ibatis.annotations.Param;
import org.sample.domain.ProductVO;

public interface ProductMapper {

	public void insertProduct(ProductVO product);
	public ProductVO readProduct(@Param("productid") Long productid);
	public int updateProduct(ProductVO product);
	public int deleteProduct(@Param("productid") Long productid);
	public int getProductById(@Param("productid") Long productid);
}
