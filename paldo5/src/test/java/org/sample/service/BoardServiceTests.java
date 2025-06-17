package org.sample.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.PostDTO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;
import org.sample.mapper.BoardMapper;
import org.sample.mapper.BoardMapperTests;
import org.sample.mapper.ImgPathMapper;
import org.sample.mapper.ProductMapper;
import org.sample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@Transactional
public class BoardServiceTests {

	@Autowired
    private BoardService boardService;

    @Autowired
    private BoardMapper boardMapper;

    private PostDTO testPost;

/*    @Before
    public void setup() {
        UserVO user = UserVO.builder()
                .nickname("테스트유저")
                .email("test@user.com")
                .pwd("1234")
                .build();

        ProductVO product = ProductVO.builder()
                .modelName("아이폰 14")
                .description("중고폰")
                .price(400000L)
                .condition("B급")
                .brand("Apple")
                .build();

        BoardVO board = BoardVO.builder()
                .status("판매중")
                .title("아이폰 팝니다")
                .build();

        ImgPathVO img = ImgPathVO.builder()
                .imgPath("/img/test.jpg")
                .build();

        testPost = PostDTO.builder()
                .user(user)
                .product(product)
                .board(board)
                .imgPaths(List.of(img))
                .build();
    }
*/
    @Test
    public void testRegisterAndGet() {
        boardService.register(testPost);

        Long boardid = boardMapper.getList(new Criteria(1, 1)).get(0).getBoardid();
        PostDTO result = boardService.get(boardid);

        assertNotNull(result);
        assertEquals("아이폰 팝니다", result.getBoard().getTitle());
        assertEquals("테스트유저", result.getUser().getNickname());
        assertEquals("Apple", result.getProduct().getBrand());
        assertFalse(result.getImgPaths().isEmpty());
    }
    
    @Test
    public void testModify() {
        boardService.register(testPost);
        Long boardid = boardMapper.getList(new Criteria(1, 1)).get(0).getBoardid();

        PostDTO postToModify = boardService.get(boardid);
        postToModify.getBoard().setTitle("아이폰 팔렸습니다");
        postToModify.getBoard().setStatus("판매완료");

        boolean updated = boardService.modify(postToModify);
        assertTrue(updated);

        PostDTO modified = boardService.get(boardid);
        assertEquals("아이폰 팔렸습니다", modified.getBoard().getTitle());
        assertEquals("판매완료", modified.getBoard().getStatus());
    }

    @Test
    public void testDelete() {
        boardService.register(testPost);

        Long boardid = boardMapper.getList(new Criteria(1, 1)).get(0).getBoardid();
        assertNotNull(boardService.get(boardid));

        boardService.remove(boardid);
        
        // 안전하게 null 여부 체크
        PostDTO deleted = boardService.get(boardid);
        assertNull("삭제 후에도 게시글이 존재합니다.", deleted);
    }
}

