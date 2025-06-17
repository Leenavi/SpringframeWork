package org.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.PostDTO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;
import org.sample.mapper.BoardMapper;
import org.sample.mapper.ImgPathMapper;
import org.sample.mapper.ProductMapper;
import org.sample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Builder;

@Service
public class BoardServiceImpl implements BoardService {

   @Autowired
    private BoardMapper boardMapper;
   @Autowired
    private ImgPathMapper imgPathMapper;
   @Autowired
    private ProductMapper productMapper;
   @Autowired
    private UserMapper userMapper;

    // 의존성 주입 (Setter Injection)
    public void setBoardMapper(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public void setImgPathMapper(ImgPathMapper imgPathMapper) {
        this.imgPathMapper = imgPathMapper;
    }

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    // 게시글 등록
    @Override
    @Transactional
    public void register(PostDTO postDTO) {
        UserVO user = postDTO.getUser();
        ProductVO product = postDTO.getProduct();
        BoardVO board = postDTO.getBoard();
        List<ImgPathVO> imgPaths = postDTO.getImgPaths();

        userMapper.insertUser(user);
        productMapper.insertProduct(product);

        board.setUserid(user.getUserid());
        board.setProductid(product.getProductid());
        boardMapper.insertBoard(board);

        for (ImgPathVO img : imgPaths) {
            img.setProductid(product.getProductid());
            imgPathMapper.insertImgPath(img);
        }
    }

    @Override
    public PostDTO get(Long boardid) {//게시글 조회
        BoardVO board = boardMapper.readBoard(boardid);
//        if (board == null) return null;

        return PostDTO.builder()
                .board(board)
                .user(userMapper.readUser(board.getUserid()))// 사용자 정보 세팅
                .product(productMapper.readProduct(board.getProductid()))// 상품 정보 세팅
                .imgPaths(imgPathMapper.readImgPath(board.getProductid()))// 이미지 리스트 세팅
                .build();
    }


    @Transactional
    @Override
    public boolean modify(PostDTO postDTO) {
        BoardVO board = postDTO.getBoard();
        UserVO user = postDTO.getUser();
        ProductVO product = postDTO.getProduct();
        List<ImgPathVO> imgPaths = postDTO.getImgPaths();
       
        
        boolean boardUpdated = boardMapper.updateBoard(postDTO) > 0;
        boolean userUpdated = userMapper.updateUser(user) > 0;
        boolean productUpdated = productMapper.updateProduct(product) > 0;

        imgPathMapper.deleteImgPath(product.getProductid());
        for (ImgPathVO img : imgPaths) {
            img.setProductid(product.getProductid());
            imgPathMapper.insertImgPath(img);
        }

        return boardUpdated && userUpdated && productUpdated;
    }

 // 게시글 삭제
    @Transactional
    @Override
    public boolean remove(Long boardid) {
        BoardVO board = boardMapper.readBoard(boardid);
        if (board == null) return false;

        Long productId = board.getProductid();
        imgPathMapper.deleteImgPath(productId);
        productMapper.deleteProduct(productId);

        return boardMapper.deleteBoard(boardid) > 0;
    }

 // 전체 게시글 목록 (PostDTO로 묶어서 반환)
    @Override
    public List<PostDTO> getPostList(Criteria cri) {
        List<BoardVO> boards = boardMapper.getList(cri);
        List<PostDTO> posts = new ArrayList<>();

        for (BoardVO board : boards) {
            PostDTO dto = PostDTO.builder()
                    .board(board)
                    .user(userMapper.readUser(board.getUserid()))
                    .product(productMapper.readProduct(board.getProductid()))
                    .imgPaths(imgPathMapper.readImgPath(board.getProductid()))
                    .build();
            posts.add(dto);
        }
        return posts;
    }

    @Override
    public int getTotal(Criteria cri) {
        return boardMapper.countTotal(cri);  // 전체 게시글 수 조회
    }

    @Override
    public void registerImgPath(ImgPathVO imgpath) {
        imgPathMapper.insertSelectKeyImgPath(imgpath);  // 이미지 경로 등록
    }

    @Override
    public void registerProduct(ProductVO product) {
        productMapper.insertProduct(product);  // 상품 등록
    }

    @Override
    public void registerUser(UserVO user) {
        userMapper.insertUser(user);  // 사용자 등록
    }

    @Override
    public List<ImgPathVO> getImgPaths(Long productid) {
        return imgPathMapper.readImgPath(productid);  // 이미지 경로 리스트 조회
    }
}