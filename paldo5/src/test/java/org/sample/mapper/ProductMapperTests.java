package org.sample.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.domain.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@Transactional
public class ProductMapperTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testInsert() {
        ProductVO vo = ProductVO.builder()
                .modelName("Test Model")
                .description("Test description")
                .price(1000L)
                .condition("new")
                .brand("TestBrand")
                .build();

        log.info("Inserted product id: " + vo);
    }

    @Test
    public void testRead() {
        ProductVO vo = productMapper.readProduct(1L);
        log.info(vo);
    }

    @Test
    public void testUpdate() {
        ProductVO vo = ProductVO.builder()
                .productid(1L)
                .modelName("Updated Model")
                .description("Updated description")
                .price(1200L)
                .condition("used")
                .brand("UpdatedBra")
                .build();

        log.info(productMapper.updateProduct(vo));

    }

    @Test
    public void testDelete() {
        int result = productMapper.deleteProduct(3L);
        log.info("Delete result: " + result);
    }
}
