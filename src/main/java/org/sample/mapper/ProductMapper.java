package org.sample.mapper;

import org.apache.ibatis.annotations.Param;
import org.sample.domain.ProductVO;

public interface ProductMapper {

	int insertProduct(ProductVO product);

    ProductVO selectProductById(@Param("productid") int productid);

    int updateProduct(ProductVO product);

    int deleteProduct(@Param("productid") int productid);
}
