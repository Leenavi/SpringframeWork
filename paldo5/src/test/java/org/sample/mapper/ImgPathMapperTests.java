package org.sample.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.domain.ImgPathVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@Transactional
public class ImgPathMapperTests {

    @Autowired
    private ImgPathMapper imgPathMapper;

    @Test
    public void testInsert() {
        ImgPathVO vo = ImgPathVO.builder()
                .productid(3L)
                .imgPath("/images/test.jpg")
                .build();

        
        log.info("Inserted imgpath id: " + vo);
    }

    @Test
    public void testReadImgPath() {
        Long productId = 1L; // 테스트용으로 DB에 존재하는 productid 입력
        List<ImgPathVO> imgPaths = imgPathMapper.readImgPath(productId);

        for (ImgPathVO vo : imgPaths) {
            log.info(vo);
        }
    }
    
    @Test
    public void testUpdateImgPath() {
    	ImgPathVO vo = ImgPathVO.builder()
                .productid(2L)
                .imgPath("/images/test.jpg")
                .build();

        log.info("Inserted imgpath id: " + vo);
    }

    @Test
    public void testDelete() {
        int result = imgPathMapper.deleteImgPath(3L);
        log.info("Delete result: " + result);
    }
}
