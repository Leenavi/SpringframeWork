package org.sample.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.domain.BoardVO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@Transactional
public class BoardMapperTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsert() {
        BoardVO vo = BoardVO.builder()
                .productid(1L)
                .userid(1L)
                .title("Test Board Title")
                .status("available")
                .build();

        boardMapper.insertBoard(vo);
    }

    @Test
    public void testRead() {
        BoardVO vo = boardMapper.readBoard(1L);
        log.info(vo);

    }

/*    @Test
    public void testUpdate() {
        BoardVO vo = BoardVO.builder()
                .boardid(1L)
                .title("Updated Title")
                .status("sold")
                .build();

        boardMapper.updateBoard(vo);
        log.info(vo);
    }*/

    @Test
    public void testDelete() {
        int result = boardMapper.deleteBoard(1L);
        log.info("Delete result: " + result);
    }
    
/*    @Test
    public void testGetListWithUserAndProduct() {
    	List<BoardVO> list = boardMapper.getList();
    	for (BoardVO board : list) {
    		log.info("제목: " + board.getTitle());
    		log.info("작성자: " + board.getUser().getNickname());
    		log.info("판매 상태: " + board.getStatus());
    		log.info("브랜드: " + board.getProduct().getBrand());
    		log.info("--------");
    	}
    }*/

}
